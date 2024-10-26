package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.TreatmentTask;

public interface TreatmentTaskService {
    TreatmentTask create(TreatmentTask treatmentTask);
    TreatmentTask update(TreatmentTask treatmentTask);
    TreatmentTask findById(Integer id);
    void deleteById(Integer id);
}
