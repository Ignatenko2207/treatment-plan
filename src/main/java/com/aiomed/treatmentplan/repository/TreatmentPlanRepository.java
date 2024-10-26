package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Integer> {
}
