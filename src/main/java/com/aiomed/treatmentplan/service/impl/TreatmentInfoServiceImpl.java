package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentInfo;
import com.aiomed.treatmentplan.repository.TreatmentInfoRepository;
import com.aiomed.treatmentplan.service.TreatmentInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TreatmentInfoServiceImpl implements TreatmentInfoService {

    private final TreatmentInfoRepository treatmentInfoRepository;

    @Transactional
    @Override
    public TreatmentInfo create(TreatmentInfo treatmentInfo) {
        try {
            if (Objects.nonNull(treatmentInfo.getId())) {
                return treatmentInfoRepository.saveAndFlush(treatmentInfo);
            }
            log.warn("TreatmentInfo id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public TreatmentInfo update(TreatmentInfo treatmentInfo) {
        try {
            if (Objects.nonNull(treatmentInfo.getId())) {
                return treatmentInfoRepository.saveAndFlush(treatmentInfo);
            }
            log.warn("TreatmentInfo id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public TreatmentInfo findById(Integer id) {
        try {
            return treatmentInfoRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (treatmentInfoRepository.existsById(id)) {
                treatmentInfoRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<TreatmentInfo> findAll() {
        try {
            return treatmentInfoRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

}
