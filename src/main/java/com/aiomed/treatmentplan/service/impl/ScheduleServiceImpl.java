package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import com.aiomed.treatmentplan.service.ScheduleService;
import com.aiomed.treatmentplan.service.TreatmentService;
import com.aiomed.treatmentplan.service.TreatmentTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final TreatmentService treatmentService;
    private final TreatmentTaskService treatmentTaskService;

    // cron can be set in properties and sequence depends on client's processes
    // every 10 minutes by default
    @Scheduled(cron = "0 */10 * * * *")
    @Override
    public void createTasksFromTreatments() {
        List<Treatment> treatments = treatmentService.findAllByStatus(TreatmentStatus.ACTIVE);
        for (Treatment treatment : treatments) {
            if (Objects.nonNull(treatment.getEndTime()) && LocalDateTime.now().isAfter(treatment.getEndTime())) {
                // such treatment should be deactivated
                treatment.setStatus(TreatmentStatus.INACTIVE);
                updateTreatment(treatment);
            } else {
                LocalDateTime treatmentTaskTime = getExecutionTime(treatment);
                if (Objects.nonNull(treatmentTaskTime)) {
                    createTreatmentTask(treatment, treatmentTaskTime);
                }
            }

        }
    }

    @Async
    public void createTreatmentTask(Treatment treatment, LocalDateTime treatmentTaskTime) {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);
        treatmentTask.setTreatmentId(treatment.getTreatmentId());
        treatmentTask.setUserId(treatment.getUserId());
        treatmentTask.setStartTime(treatmentTaskTime);
        // check for duplicates
        if (similarTaskIsNotPresent(treatmentTask)) {
            treatmentTaskService.create(treatmentTask);
        }
    }

    private boolean similarTaskIsNotPresent(TreatmentTask treatmentTask) {
        TreatmentTask result = treatmentTaskService
                .findByStatusInAndUserIdAndTreatmentIdAndStartTime(
                        List.of(TreatmentTaskStatus.CREATED, TreatmentTaskStatus.IN_WORK),
                        treatmentTask.getUserId(),
                        treatmentTask.getTreatmentId(),
                        treatmentTask.getStartTime()
                );
        return Objects.isNull(result);
    }

    @Async
    public void updateTreatment(Treatment treatment) {
        treatmentService.create(treatment);
    }

    private LocalDateTime getExecutionTime(Treatment treatment) {
        try {
            if (StringUtils.isBlank(treatment.getCronExpression())) {
                if (LocalDateTime.now().isBefore(treatment.getStartTime())) {
                    return treatment.getStartTime();
                } else {
                    if (Objects.nonNull(treatment.getEndTime()) && LocalDateTime.now().isBefore(treatment.getEndTime())) {
                        return treatment.getEndTime();
                    }
                }
            } else {
                CronExpression cronExpression = CronExpression.parse(treatment.getCronExpression());
                LocalDateTime nextTime = cronExpression.next(LocalDateTime.now());
                if (Objects.nonNull(nextTime)) {
                    if (treatment.getStartTime().isAfter(nextTime)) {
                        return cronExpression.next(treatment.getStartTime());
                    }
                    if (Objects.nonNull(treatment.getEndTime()) && nextTime.isAfter(treatment.getEndTime())) {
                        return treatment.getEndTime();
                    }
                    return nextTime;
                }
            }
        } catch (Exception e) {
            log.error("It was impossible to get the execution time of the treatment {}", treatment.getId(), e);
        }
        return null;
    }
}
