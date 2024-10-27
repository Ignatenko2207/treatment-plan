package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentTask;
import com.aiomed.treatmentplan.model.enums.TreatmentTaskStatus;
import com.aiomed.treatmentplan.service.TreatmentTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TreatmentTaskControllerTest {

    @MockBean
    private TreatmentTaskService treatmentTaskService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void create() {
        TreatmentTask requestBody = new TreatmentTask();
        requestBody.setUserId(1);
        requestBody.setTreatmentId(1);
        requestBody.setStatus(TreatmentTaskStatus.CREATED);
        requestBody.setStartTime(LocalDateTime.now());

        TreatmentTask responseBody = new TreatmentTask();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentTaskStatus.CREATED);
        responseBody.setStartTime(LocalDateTime.now());

        when(treatmentTaskService.create(any())).thenReturn(responseBody);

        final String url = "/treatment-task/create";

        ResponseEntity<TreatmentTask> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.POST, URI.create(url)),
                TreatmentTask.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentTaskService).create(any());
    }

    @Test
    void findById() {
        TreatmentTask responseBody = new TreatmentTask();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentTaskStatus.CREATED);
        responseBody.setStartTime(LocalDateTime.now());

        when(treatmentTaskService.findById(anyInt())).thenReturn(responseBody);

        final String url = "/treatment-task/find-by-id?id=1";

        ResponseEntity<TreatmentTask> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                TreatmentTask.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentTaskService).findById(anyInt());
    }

    @Test
    void update() {
        TreatmentTask requestBody = new TreatmentTask();
        requestBody.setId(1);
        requestBody.setUserId(1);
        requestBody.setTreatmentId(1);
        requestBody.setStatus(TreatmentTaskStatus.CREATED);
        requestBody.setStartTime(LocalDateTime.now());

        TreatmentTask responseBody = new TreatmentTask();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentTaskStatus.CREATED);
        responseBody.setStartTime(LocalDateTime.now());
        when(treatmentTaskService.update(any())).thenReturn(responseBody);

        final String url = "/treatment-task/update";

        ResponseEntity<TreatmentTask> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.PUT, URI.create(url)),
                TreatmentTask.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentTaskService).update(any());
    }

    @Test
    void delete() {
        final String url = "/treatment-task/delete?id=1";

        ResponseEntity<TreatmentTask> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.DELETE, URI.create(url)),
                TreatmentTask.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.isNull(response.getBody()));

        verify(treatmentTaskService).deleteById(anyInt());
    }

    @Test
    void findAllByParameters() {
        final String url = "/treatment-task/find-by-parameters?userId=1&startTime=2024-10-27%2014%3A00";

        when(treatmentTaskService.findAllByUserAndStartTime(anyInt(), any())).thenReturn(List.of(new TreatmentTask()));

        ResponseEntity<List<TreatmentTask>> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                new ParameterizedTypeReference<>() {});

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));
        assertFalse(response.getBody().isEmpty());

        verify(treatmentTaskService).findAllByUserAndStartTime(anyInt(), any());
    }

}