package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import com.aiomed.treatmentplan.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TreatmentServiceImplTest {

    @InjectMocks
    private TreatmentServiceImpl treatmentService;

    @Mock
    private TreatmentRepository treatmentRepository;

    @Test
    void create() {
        Treatment treatment = new Treatment();
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        when(treatmentRepository.saveAndFlush(any())).thenReturn(treatment);

        Treatment result = treatmentService.create(treatment);
        assertNotNull(result);

        verify(treatmentRepository).saveAndFlush(any());
    }

    @Test
    void create_withId() {
        Treatment treatment = new Treatment();
        treatment.setId(1);
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        Treatment result = treatmentService.create(treatment);
        assertNull(result);

        verifyNoInteractions(treatmentRepository);
    }

    @Test
    void update() {
        Treatment treatment = new Treatment();
        treatment.setId(1);
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        when(treatmentRepository.saveAndFlush(any())).thenReturn(treatment);

        Treatment result = treatmentService.update(treatment);
        assertNotNull(result);

        verify(treatmentRepository).saveAndFlush(any());
    }

    @Test
    void update_withoutOId() {
        Treatment treatment = new Treatment();
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        Treatment result = treatmentService.update(treatment);
        assertNull(result);

        verifyNoInteractions(treatmentRepository);
    }

    @Test
    void findById() {
        Treatment treatment = new Treatment();
        treatment.setId(1);
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        when(treatmentRepository.findById(anyInt())).thenReturn(Optional.of(treatment));

        Treatment result = treatmentService.findById(1);
        assertNotNull(result);

        verify(treatmentRepository).findById(anyInt());
    }

    @Test
    void findById_entityNotFound() {
        when(treatmentRepository.findById(anyInt())).thenReturn(Optional.empty());

        Treatment result = treatmentService.findById(1);
        assertNull(result);

        verify(treatmentRepository).findById(anyInt());
    }

    @Test
    void findAllByStatus() {
        Treatment treatment = new Treatment();
        treatment.setId(1);
        treatment.setStartTime(LocalDateTime.now());
        treatment.setUserId(1);
        treatment.setTreatmentId(1);
        treatment.setStatus(TreatmentStatus.ACTIVE);
        treatment.setRepeatable(false);

        when(treatmentRepository.findAllByStatus(any())).thenReturn(List.of(treatment));

        List<Treatment> result = treatmentService.findAllByStatus(TreatmentStatus.ACTIVE);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(treatmentRepository).findAllByStatus(any());
    }
}