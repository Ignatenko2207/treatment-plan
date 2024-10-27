package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import com.aiomed.treatmentplan.repository.TreatmentTaskRepository;
import com.aiomed.treatmentplan.service.TreatmentTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreatmentTaskServiceImpl implements TreatmentTaskService {

    private final TreatmentTaskRepository treatmentTaskRepository;

    @Transactional
    @Override
    public TreatmentTask create(TreatmentTask treatmentTask) {
        try {
            if (Objects.isNull(treatmentTask.getId())) {
                return treatmentTaskRepository.saveAndFlush(treatmentTask);
            }
            log.warn("TreatmentTask id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public TreatmentTask update(TreatmentTask treatmentTask) {
        try {
            if (Objects.nonNull(treatmentTask.getId())) {
                return treatmentTaskRepository.saveAndFlush(treatmentTask);
            }
            log.warn("TreatmentTask id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public TreatmentTask findById(Integer id) {
        try {
            return treatmentTaskRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (treatmentTaskRepository.existsById(id)) {
                treatmentTaskRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<TreatmentTask> findAllByUserAndStartTime(Integer userId, String startTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(startTime, formatter);
            return treatmentTaskRepository.findAllByUserIdAndStartTimeAfterAndStatus(userId, time, TreatmentTaskStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public TreatmentTask findByStatusInAndUserIdAndTreatmentIdAndStartTime(List<TreatmentTaskStatus> statuses, Integer userId, Integer treatmentId, LocalDateTime startTime) {
        try {
            return treatmentTaskRepository.findByStatusInAndUserIdAndTreatmentIdAndStartTime(
                    statuses, userId, treatmentId, startTime).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
