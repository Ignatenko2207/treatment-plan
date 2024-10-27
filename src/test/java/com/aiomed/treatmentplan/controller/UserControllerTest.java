package com.aiomed.treatmentplan.controller;

import com.aiomed.treatmentplan.model.User;
import com.aiomed.treatmentplan.model.enums.Gender;
import com.aiomed.treatmentplan.model.enums.UserRole;
import com.aiomed.treatmentplan.service.UserService;
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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void create() {
        User requestBody = new User();
        requestBody.setUserRole(UserRole.PATIENT);
        requestBody.setGender(Gender.MALE);
        requestBody.setFirstName("Test");
        requestBody.setLastName("Test");

        User responseBody = new User();
        responseBody.setId(1);
        responseBody.setUserRole(UserRole.PATIENT);
        responseBody.setGender(Gender.MALE);
        responseBody.setFirstName("Test");
        responseBody.setLastName("Test");

        when(userService.create(any())).thenReturn(responseBody);

        final String url = "/user/create";

        ResponseEntity<User> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.POST, URI.create(url)),
                User.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(userService).create(any());
    }

    @Test
    void findById() {
        User responseBody = new User();
        responseBody.setId(1);
        responseBody.setUserRole(UserRole.PATIENT);
        responseBody.setGender(Gender.MALE);
        responseBody.setFirstName("Test");
        responseBody.setLastName("Test");

        when(userService.findById(anyInt())).thenReturn(responseBody);

        final String url = "/user/find-by-id?id=1";

        ResponseEntity<User> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.GET, URI.create(url)),
                User.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(userService).findById(anyInt());
    }

    @Test
    void update() {
        User requestBody = new User();
        requestBody.setId(1);
        requestBody.setUserRole(UserRole.PATIENT);
        requestBody.setGender(Gender.MALE);
        requestBody.setFirstName("Test");
        requestBody.setLastName("Test");

        User responseBody = new User();
        responseBody.setId(1);
        responseBody.setUserRole(UserRole.PATIENT);
        responseBody.setGender(Gender.MALE);
        responseBody.setFirstName("Test");
        responseBody.setLastName("Test");

        when(userService.update(any())).thenReturn(responseBody);

        final String url = "/user/update";

        ResponseEntity<User> response = testRestTemplate.exchange(
                new RequestEntity<>(requestBody, HttpMethod.PUT, URI.create(url)),
                User.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.nonNull(response.getBody()));

        verify(userService).update(any());
    }

    @Test
    void delete() {
        final String url = "/user/delete?id=1";

        ResponseEntity<User> response = testRestTemplate.exchange(
                new RequestEntity<>(HttpMethod.DELETE, URI.create(url)),
                User.class);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(Objects.isNull(response.getBody()));

        verify(userService).deleteById(anyInt());
    }
}