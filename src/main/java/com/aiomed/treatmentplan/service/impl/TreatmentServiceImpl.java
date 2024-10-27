package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import com.aiomed.treatmentplan.repository.TreatmentRepository;
import com.aiomed.treatmentplan.service.TreatmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Transactional
    @Override
    public Treatment create(Treatment treatment) {
        try {
            if (Objects.isNull(treatment.getId())) {
                if (treatment.getRepeatable() && StringUtils.isBlank(treatment.getCronExpression())) {
                    log.warn("Treatment with repeatable property" +
                            " cannot be created without cron expression");
                    return null;
                }
                return treatmentRepository.saveAndFlush(treatment);
            }
            log.warn("Treatment id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public Treatment update(Treatment treatment) {
        try {
            if (Objects.nonNull(treatment.getId())) {
                if (treatment.getRepeatable() && StringUtils.isBlank(treatment.getCronExpression())) {
                    log.warn("Treatment with repeatable property" +
                            " cannot be updated without cron expression");
                    return null;
                }
                return treatmentRepository.saveAndFlush(treatment);
            }
            log.warn("Treatment id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Treatment findById(Integer id) {
        try {
            return treatmentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (treatmentRepository.existsById(id)) {
                treatmentRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Treatment> findAllByStatus(TreatmentStatus treatmentStatus) {
        try {
            return treatmentRepository.findAllByStatus(treatmentStatus);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

}
