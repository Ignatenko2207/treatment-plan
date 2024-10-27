package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentInfo;
import com.aiomed.treatmentplan.repository.TreatmentInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TreatmentInfoServiceImplTest {

    @InjectMocks
    private TreatmentInfoServiceImpl treatmentInfoService;

    @Mock
    private TreatmentInfoRepository treatmentInfoRepository;

    @Test
    void create() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        when(treatmentInfoRepository.saveAndFlush(any())).thenReturn(treatmentInfo);

        TreatmentInfo result = treatmentInfoService.create(treatmentInfo);
        assertNotNull(result);

        verify(treatmentInfoRepository).saveAndFlush(any());
    }

    @Test
    void create_withId() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setId(1);
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        TreatmentInfo result = treatmentInfoService.create(treatmentInfo);
        assertNull(result);

        verifyNoInteractions(treatmentInfoRepository);
    }

    @Test
    void update() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setId(1);
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        when(treatmentInfoRepository.saveAndFlush(any())).thenReturn(treatmentInfo);

        TreatmentInfo result = treatmentInfoService.update(treatmentInfo);
        assertNotNull(result);

        verify(treatmentInfoRepository).saveAndFlush(any());
    }

    @Test
    void update_withoutOId() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        TreatmentInfo result = treatmentInfoService.update(treatmentInfo);
        assertNull(result);

        verifyNoInteractions(treatmentInfoRepository);
    }

    @Test
    void findById() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setId(1);
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        when(treatmentInfoRepository.findById(anyInt())).thenReturn(Optional.of(treatmentInfo));

        TreatmentInfo result = treatmentInfoService.findById(1);
        assertNotNull(result);

        verify(treatmentInfoRepository).findById(anyInt());
    }

    @Test
    void findById_entityNotFound() {
        when(treatmentInfoRepository.findById(anyInt())).thenReturn(Optional.empty());

        TreatmentInfo result = treatmentInfoService.findById(1);
        assertNull(result);

        verify(treatmentInfoRepository).findById(anyInt());
    }

    @Test
    void findAllByStatus() {
        TreatmentInfo treatmentInfo = new TreatmentInfo();
        treatmentInfo.setId(1);
        treatmentInfo.setDescription("ActionA");
        treatmentInfo.setDuration(30);

        when(treatmentInfoRepository.findAll()).thenReturn(List.of(treatmentInfo));

        List<TreatmentInfo> result = treatmentInfoService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(treatmentInfoRepository).findAll();
    }
}