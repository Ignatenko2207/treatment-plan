package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentPlan;
import com.aiomed.treatmentplan.model.enums.TreatmentPlanStatus;
import com.aiomed.treatmentplan.repository.TreatmentPlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TreatmentPlanServiceImplTest {

    @InjectMocks
    private TreatmentPlanServiceImpl treatmentPlanService;

    @Mock
    private TreatmentPlanRepository treatmentPlanRepository;

    @Test
    void create() {
        TreatmentPlan treatmentPlan = new TreatmentPlan();
        treatmentPlan.setUserId(1);
        treatmentPlan.setStartTime(LocalDateTime.now());
        treatmentPlan.setEndTime(LocalDateTime.now().plusDays(1));
        treatmentPlan.setStatus(TreatmentPlanStatus.ACTIVE);
        treatmentPlan.setTreatments(new ArrayList<>());

        when(treatmentPlanRepository.saveAndFlush(any())).thenReturn(treatmentPlan);

        TreatmentPlan result = treatmentPlanService.create(treatmentPlan);
        assertNotNull(result);

        verify(treatmentPlanRepository).saveAndFlush(any());
    }

    @Test
    void create_withId() {
        TreatmentPlan treatmentPlan = new TreatmentPlan();
        treatmentPlan.setId(1);
        treatmentPlan.setUserId(1);
        treatmentPlan.setStartTime(LocalDateTime.now());
        treatmentPlan.setEndTime(LocalDateTime.now().plusDays(1));
        treatmentPlan.setStatus(TreatmentPlanStatus.ACTIVE);
        treatmentPlan.setTreatments(new ArrayList<>());

        TreatmentPlan result = treatmentPlanService.create(treatmentPlan);
        assertNull(result);

        verifyNoInteractions(treatmentPlanRepository);
    }

    @Test
    void update() {
        TreatmentPlan treatmentPlan = new TreatmentPlan();
        treatmentPlan.setId(1);
        treatmentPlan.setUserId(1);
        treatmentPlan.setStartTime(LocalDateTime.now());
        treatmentPlan.setEndTime(LocalDateTime.now().plusDays(1));
        treatmentPlan.setStatus(TreatmentPlanStatus.ACTIVE);
        treatmentPlan.setTreatments(new ArrayList<>());

        when(treatmentPlanRepository.saveAndFlush(any())).thenReturn(treatmentPlan);

        TreatmentPlan result = treatmentPlanService.update(treatmentPlan);
        assertNotNull(result);

        verify(treatmentPlanRepository).saveAndFlush(any());
    }

    @Test
    void update_withoutOId() {
        TreatmentPlan treatmentPlan = new TreatmentPlan();
        treatmentPlan.setUserId(1);
        treatmentPlan.setStartTime(LocalDateTime.now());
        treatmentPlan.setEndTime(LocalDateTime.now().plusDays(1));
        treatmentPlan.setStatus(TreatmentPlanStatus.ACTIVE);
        treatmentPlan.setTreatments(new ArrayList<>());

        TreatmentPlan result = treatmentPlanService.update(treatmentPlan);
        assertNull(result);

        verifyNoInteractions(treatmentPlanRepository);
    }

    @Test
    void findById() {
        TreatmentPlan treatmentPlan = new TreatmentPlan();
        treatmentPlan.setId(1);
        treatmentPlan.setUserId(1);
        treatmentPlan.setStartTime(LocalDateTime.now());
        treatmentPlan.setEndTime(LocalDateTime.now().plusDays(1));
        treatmentPlan.setStatus(TreatmentPlanStatus.ACTIVE);
        treatmentPlan.setTreatments(new ArrayList<>());

        when(treatmentPlanRepository.findById(anyInt())).thenReturn(Optional.of(treatmentPlan));

        TreatmentPlan result = treatmentPlanService.findById(1);
        assertNotNull(result);

        verify(treatmentPlanRepository).findById(anyInt());
    }

    @Test
    void findById_entityNotFound() {
        when(treatmentPlanRepository.findById(anyInt())).thenReturn(Optional.empty());

        TreatmentPlan result = treatmentPlanService.findById(1);
        assertNull(result);

        verify(treatmentPlanRepository).findById(anyInt());
    }

}