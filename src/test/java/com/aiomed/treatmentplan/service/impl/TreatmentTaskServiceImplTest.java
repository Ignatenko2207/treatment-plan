package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import com.aiomed.treatmentplan.repository.TreatmentTaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class TreatmentTaskServiceImplTest {

    @InjectMocks
    private TreatmentTaskServiceImpl treatmentTaskService;

    @Mock
    private TreatmentTaskRepository treatmentTaskRepository;

    @Test
    void create() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        when(treatmentTaskRepository.saveAndFlush(any())).thenReturn(treatmentTask);

        TreatmentTask result = treatmentTaskService.create(treatmentTask);
        assertNotNull(result);

        verify(treatmentTaskRepository).saveAndFlush(any());
    }

    @Test
    void create_withId() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setId(1);
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        TreatmentTask result = treatmentTaskService.create(treatmentTask);
        assertNull(result);

        verifyNoInteractions(treatmentTaskRepository);
    }

    @Test
    void update() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setId(1);
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        when(treatmentTaskRepository.saveAndFlush(any())).thenReturn(treatmentTask);

        TreatmentTask result = treatmentTaskService.update(treatmentTask);
        assertNotNull(result);

        verify(treatmentTaskRepository).saveAndFlush(any());
    }

    @Test
    void update_withoutOId() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        TreatmentTask result = treatmentTaskService.update(treatmentTask);
        assertNull(result);

        verifyNoInteractions(treatmentTaskRepository);
    }

    @Test
    void findById() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setId(1);
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        when(treatmentTaskRepository.findById(anyInt())).thenReturn(Optional.of(treatmentTask));

        TreatmentTask result = treatmentTaskService.findById(1);
        assertNotNull(result);

        verify(treatmentTaskRepository).findById(anyInt());
    }

    @Test
    void findById_entityNotFound() {
        when(treatmentTaskRepository.findById(anyInt())).thenReturn(Optional.empty());

        TreatmentTask result = treatmentTaskService.findById(1);
        assertNull(result);

        verify(treatmentTaskRepository).findById(anyInt());
    }

    @Test
    void findAllByUserAndStartTime() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setId(1);
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        when(treatmentTaskRepository.findAllByUserIdAndStartTimeAfterAndStatus(anyInt(), any(), any())).thenReturn(List.of(treatmentTask));

        List<TreatmentTask> result = treatmentTaskService.findAllByUserAndStartTime(1, "2024-11-27 20:00");
        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(treatmentTaskRepository).findAllByUserIdAndStartTimeAfterAndStatus(anyInt(), any(), any());
    }

    @Test
    void findAllByUserAndStartTime_entityNotFound() {
        when(treatmentTaskRepository.findAllByUserIdAndStartTimeAfterAndStatus(anyInt(), any(), any())).thenReturn(Collections.emptyList());

        List<TreatmentTask> result = treatmentTaskService.findAllByUserAndStartTime(1, "2024-11-27 20:00");
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(treatmentTaskRepository).findAllByUserIdAndStartTimeAfterAndStatus(anyInt(), any(), any());
    }

    @Test
    void findByStatusInAndUserIdAndTreatmentIdAndStartTime() {
        TreatmentTask treatmentTask = new TreatmentTask();
        treatmentTask.setId(1);
        treatmentTask.setUserId(1);
        treatmentTask.setTreatmentId(1);
        treatmentTask.setStartTime(LocalDateTime.now());
        treatmentTask.setStatus(TreatmentTaskStatus.CREATED);

        when(treatmentTaskRepository
                .findByStatusInAndUserIdAndTreatmentIdAndStartTime(any(), anyInt(), any(), any()))
                .thenReturn(Optional.of(treatmentTask));

        TreatmentTask result = treatmentTaskService
                .findByStatusInAndUserIdAndTreatmentIdAndStartTime(
                        List.of(TreatmentTaskStatus.CREATED, TreatmentTaskStatus.IN_WORK),
                        1,
                        1,
                        LocalDateTime.now()
                );
        assertNotNull(result);

        verify(treatmentTaskRepository).findByStatusInAndUserIdAndTreatmentIdAndStartTime(any(), anyInt(), any(), any());
    }

    @Test
    void findByStatusInAndUserIdAndTreatmentIdAndStartTime_entityNotFound() {
        when(treatmentTaskRepository
                .findByStatusInAndUserIdAndTreatmentIdAndStartTime(any(), anyInt(), any(), any()))
                .thenReturn(Optional.empty());

        TreatmentTask result = treatmentTaskService
                .findByStatusInAndUserIdAndTreatmentIdAndStartTime(
                        List.of(TreatmentTaskStatus.CREATED, TreatmentTaskStatus.IN_WORK),
                        1,
                        1,
                        LocalDateTime.now()
                );
        assertNull(result);

        verify(treatmentTaskRepository).findByStatusInAndUserIdAndTreatmentIdAndStartTime(any(), anyInt(), any(), any());

    }
}