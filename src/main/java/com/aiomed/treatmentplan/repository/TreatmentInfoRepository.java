package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.TreatmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentInfoRepository extends JpaRepository<TreatmentInfo, Integer> {
}
