package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findAllByStatus(TreatmentStatus treatmentStatus);

}
