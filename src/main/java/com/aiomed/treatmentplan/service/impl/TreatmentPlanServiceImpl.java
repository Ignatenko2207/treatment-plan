package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentPlan;
import com.aiomed.treatmentplan.repository.TreatmentPlanRepository;
import com.aiomed.treatmentplan.service.TreatmentPlanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreatmentPlanServiceImpl implements TreatmentPlanService {

    private final TreatmentPlanRepository treatmentPlanRepository;

    @Transactional
    @Override
    public TreatmentPlan create(TreatmentPlan treatmentPlan) {
        try {
            if (Objects.nonNull(treatmentPlan.getId())) {
                return treatmentPlanRepository.saveAndFlush(treatmentPlan);
            }
            log.warn("TreatmentPlan id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public TreatmentPlan update(TreatmentPlan treatmentPlan) {
        try {
            if (Objects.nonNull(treatmentPlan.getId())) {
                return treatmentPlanRepository.saveAndFlush(treatmentPlan);
            }
            log.warn("TreatmentPlan id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public TreatmentPlan findById(Integer id) {
        try {
            return treatmentPlanRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (treatmentPlanRepository.existsById(id)) {
                treatmentPlanRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
