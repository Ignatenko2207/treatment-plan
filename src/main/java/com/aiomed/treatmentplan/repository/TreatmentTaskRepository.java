package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Integer> {

    Optional<TreatmentTask> findByStatusInAndUserIdAndTreatmentIdAndStartTime(List<TreatmentTaskStatus> statuses,
                                                                             Integer userId,
                                                                             Integer treatmentId,
                                                                             LocalDateTime startTime);

    List<TreatmentTask> findAllByUserIdAndStartTimeAfterAndStatus
            (Integer userId,
             LocalDateTime startTime,
             TreatmentTaskStatus status);
}
