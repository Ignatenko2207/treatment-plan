package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.TreatmentPlan;

public interface TreatmentPlanService {
    TreatmentPlan create(TreatmentPlan treatmentPlan);
    TreatmentPlan update(TreatmentPlan treatmentPlan);
    TreatmentPlan findById(Integer id);
    void deleteById(Integer id);
}
