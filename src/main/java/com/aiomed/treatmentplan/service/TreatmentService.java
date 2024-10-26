package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.Treatment;

public interface TreatmentService {
    Treatment create(Treatment treatment);
    Treatment update(Treatment treatment);
    Treatment findById(Integer id);
    void deleteById(Integer id);
}
