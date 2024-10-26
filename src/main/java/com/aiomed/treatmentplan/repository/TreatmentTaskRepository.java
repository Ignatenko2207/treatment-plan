package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.TreatmentTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Integer> {
}
