package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentPlan;
import com.aiomed.treatmentplan.model.enums.TreatmentPlanStatus;
import com.aiomed.treatmentplan.service.TreatmentPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TreatmentPlanControllerTest {

    @MockBean
    private TreatmentPlanService treatmentPlanService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void create() {
        TreatmentPlan requestBody = new TreatmentPlan();
        requestBody.setUserId(1);
        requestBody.setStartTime(LocalDateTime.now());
        requestBody.setEndTime(LocalDateTime.now().plusDays(1));
        requestBody.setStatus(TreatmentPlanStatus.ACTIVE);
        requestBody.setTreatments(new ArrayList<>());

        TreatmentPlan responseBody = new TreatmentPlan();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setEndTime(LocalDateTime.now().plusDays(1));
        responseBody.setStatus(TreatmentPlanStatus.ACTIVE);
        responseBody.setTreatments(new ArrayList<>());

        when(treatmentPlanService.create(any())).thenReturn(responseBody);

        final String url = "/treatment-plan/create";

        ResponseEntity<TreatmentPlan> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.POST, URI.create(url)),
                TreatmentPlan.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentPlanService).create(any());
    }

    @Test
    void findById() {
        TreatmentPlan responseBody = new TreatmentPlan();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setEndTime(LocalDateTime.now().plusDays(1));
        responseBody.setStatus(TreatmentPlanStatus.ACTIVE);
        responseBody.setTreatments(new ArrayList<>());

        when(treatmentPlanService.findById(anyInt())).thenReturn(responseBody);

        final String url = "/treatment-plan/find-by-id?id=1";

        ResponseEntity<TreatmentPlan> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                TreatmentPlan.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentPlanService).findById(anyInt());
    }

    @Test
    void update() {
        TreatmentPlan requestBody = new TreatmentPlan();
        requestBody.setId(1);
        requestBody.setUserId(1);
        requestBody.setStartTime(LocalDateTime.now());
        requestBody.setEndTime(LocalDateTime.now().plusDays(1));
        requestBody.setStatus(TreatmentPlanStatus.ACTIVE);
        requestBody.setTreatments(new ArrayList<>());

        TreatmentPlan responseBody = new TreatmentPlan();
        responseBody.setId(1);
        responseBody.setUserId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setEndTime(LocalDateTime.now().plusDays(1));
        responseBody.setStatus(TreatmentPlanStatus.ACTIVE);
        responseBody.setTreatments(new ArrayList<>());

        when(treatmentPlanService.update(any())).thenReturn(responseBody);

        final String url = "/treatment-plan/update";

        ResponseEntity<TreatmentPlan> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.PUT, URI.create(url)),
                TreatmentPlan.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentPlanService).update(any());
    }

    @Test
    void delete() {
        final String url = "/treatment-plan/delete?id=1";

        ResponseEntity<TreatmentPlan> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.DELETE, URI.create(url)),
                TreatmentPlan.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.isNull(response.getBody()));

        verify(treatmentPlanService).deleteById(anyInt());
    }

}