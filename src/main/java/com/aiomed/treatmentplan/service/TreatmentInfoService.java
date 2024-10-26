package com.aiomed.treatmentplan.service;

import com.aiomed.treatmentplan.model.TreatmentInfo;

import java.util.List;

public interface TreatmentInfoService {
    TreatmentInfo create(TreatmentInfo treatmentInfo);
    TreatmentInfo update(TreatmentInfo treatmentInfo);
    TreatmentInfo findById(Integer id);
    void deleteById(Integer id);
    List<TreatmentInfo> findAll();

}
