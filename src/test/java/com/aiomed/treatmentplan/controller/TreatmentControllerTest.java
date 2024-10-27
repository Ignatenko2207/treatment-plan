package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.Treatment;
import com.aiomed.treatmentplan.model.enums.TreatmentStatus;
import com.aiomed.treatmentplan.service.TreatmentService;
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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TreatmentControllerTest {

    @MockBean
    private TreatmentService treatmentService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void create() {
        Treatment requestBody = new Treatment();
        requestBody.setStartTime(LocalDateTime.now());
        requestBody.setUserId(1);
        requestBody.setTreatmentId(1);
        requestBody.setStatus(TreatmentStatus.ACTIVE);
        requestBody.setRepeatable(false);

        Treatment responseBody = new Treatment();
        responseBody.setId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentStatus.ACTIVE);
        responseBody.setRepeatable(false);

        when(treatmentService.create(any())).thenReturn(responseBody);

        final String url = "/treatment/create";

        ResponseEntity<Treatment> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.POST, URI.create(url)),
                Treatment.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentService).create(any());
    }

    @Test
    void findById() {
        Treatment responseBody = new Treatment();
        responseBody.setId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentStatus.ACTIVE);
        responseBody.setRepeatable(false);
        when(treatmentService.findById(anyInt())).thenReturn(responseBody);

        final String url = "/treatment/find-by-id?id=1";

        ResponseEntity<Treatment> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                Treatment.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentService).findById(anyInt());
    }

    @Test
    void update() {
        Treatment requestBody = new Treatment();
        requestBody.setId(1);
        requestBody.setStartTime(LocalDateTime.now());
        requestBody.setUserId(1);
        requestBody.setTreatmentId(1);
        requestBody.setStatus(TreatmentStatus.ACTIVE);
        requestBody.setRepeatable(false);

        Treatment responseBody = new Treatment();
        responseBody.setId(1);
        responseBody.setStartTime(LocalDateTime.now());
        responseBody.setUserId(1);
        responseBody.setTreatmentId(1);
        responseBody.setStatus(TreatmentStatus.ACTIVE);
        responseBody.setRepeatable(false);

        when(treatmentService.update(any())).thenReturn(responseBody);

        final String url = "/treatment/update";

        ResponseEntity<Treatment> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.PUT, URI.create(url)),
                Treatment.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentService).update(any());
    }

    @Test
    void delete() {
        final String url = "/treatment/delete?id=1";

        ResponseEntity<Treatment> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.DELETE, URI.create(url)),
                Treatment.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.isNull(response.getBody()));

        verify(treatmentService).deleteById(anyInt());
    }
}