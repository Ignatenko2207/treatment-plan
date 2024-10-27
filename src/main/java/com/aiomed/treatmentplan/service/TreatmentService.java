package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;

import java.util.List;

public interface TreatmentService {
    Treatment create(Treatment treatment);
    Treatment update(Treatment treatment);
    Treatment findById(Integer id);
    void deleteById(Integer id);

    List<Treatment> findAllByStatus(TreatmentStatus treatmentStatus);
}
