package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface TreatmentTaskService {
    TreatmentTask create(TreatmentTask treatmentTask);
    TreatmentTask update(TreatmentTask treatmentTask);
    TreatmentTask findById(Integer id);
    void deleteById(Integer id);
    List<TreatmentTask> findAllByUserAndStartTime(Integer userId, String startTime);
    TreatmentTask findByStatusInAndUserIdAndTreatmentIdAndStartTime(List<TreatmentTaskStatus> created, Integer userId, Integer treatmentId, LocalDateTime startTime);

}
