package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.repository.TreatmentTaskRepository;
import com.aiomed.treatmentplan.service.TreatmentTaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreatmentTaskServiceImpl implements TreatmentTaskService {

    private final TreatmentTaskRepository treatmentTaskRepository;

    @Transactional
    @Override
    public TreatmentTask create(TreatmentTask treatmentTask) {
        try {
            if (Objects.nonNull(treatmentTask.getId())) {
                return treatmentTaskRepository.saveAndFlush(treatmentTask);
            }
            log.warn("TreatmentTask id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public TreatmentTask update(TreatmentTask treatmentTask) {
        try {
            if (Objects.nonNull(treatmentTask.getId())) {
                return treatmentTaskRepository.saveAndFlush(treatmentTask);
            }
            log.warn("TreatmentTask id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public TreatmentTask findById(Integer id) {
        try {
            return treatmentTaskRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (treatmentTaskRepository.existsById(id)) {
                treatmentTaskRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
