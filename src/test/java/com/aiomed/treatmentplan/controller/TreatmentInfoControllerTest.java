package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.TreatmentInfo;
import com.aiomed.treatmentplan.service.TreatmentInfoService;
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
class TreatmentInfoControllerTest {

    @MockBean
    private TreatmentInfoService treatmentInfoService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void create() {
        TreatmentInfo requestBody = new TreatmentInfo();
        requestBody.setDescription("ActionA");
        requestBody.setDuration(30);

        TreatmentInfo responseBody = new TreatmentInfo();
        responseBody.setId(1);
        responseBody.setDescription("ActionA");
        responseBody.setDuration(30);

        when(treatmentInfoService.create(any())).thenReturn(responseBody);

        final String url = "/treatment-info/create";

        ResponseEntity<TreatmentInfo> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.POST, URI.create(url)),
                TreatmentInfo.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentInfoService).create(any());
    }

    @Test
    void findById() {
        TreatmentInfo responseBody = new TreatmentInfo();
        responseBody.setId(1);
        responseBody.setDescription("ActionA");
        responseBody.setDuration(30);

        when(treatmentInfoService.findById(anyInt())).thenReturn(responseBody);

        final String url = "/treatment-info/find-by-id?id=1";

        ResponseEntity<TreatmentInfo> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                TreatmentInfo.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentInfoService).findById(anyInt());
    }

    @Test
    void update() {
        TreatmentInfo requestBody = new TreatmentInfo();
        requestBody.setId(1);
        requestBody.setDescription("ActionA");
        requestBody.setDuration(30);

        TreatmentInfo responseBody = new TreatmentInfo();
        responseBody.setId(1);
        responseBody.setDescription("ActionA");
        responseBody.setDuration(30);

        when(treatmentInfoService.update(any())).thenReturn(responseBody);

        final String url = "/treatment-info/update";

        ResponseEntity<TreatmentInfo> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.PUT, URI.create(url)),
                TreatmentInfo.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(treatmentInfoService).update(any());
    }

    @Test
    void delete() {
        final String url = "/treatment-info/delete?id=1";

        ResponseEntity<TreatmentInfo> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.DELETE, URI.create(url)),
                TreatmentInfo.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.isNull(response.getBody()));

        verify(treatmentInfoService).deleteById(anyInt());
    }

    @Test
    void findAll() {
        final String url = "/treatment-info/find-all";

        when(treatmentInfoService.findAll()).thenReturn(List.of(new TreatmentInfo()));

        ResponseEntity<List<TreatmentInfo>> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                new ParameterizedTypeReference<>() {});

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));
        assertFalse(response.getBody().isEmpty());

        verify(treatmentInfoService).findAll();
    }

}