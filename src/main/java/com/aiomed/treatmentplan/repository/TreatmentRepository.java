package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}
