package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.User;
import com.aiomed.treatmentplan.model.enums.Gender;
import com.aiomed.treatmentplan.model.enums.UserRole;
import com.aiomed.treatmentplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void create() {
        User user = new User();
        user.setUserRole(UserRole.PATIENT);
        user.setGender(Gender.MALE);
        user.setFirstName("Test");
        user.setLastName("Test");

        when(userRepository.saveAndFlush(any())).thenReturn(user);

        User result = userService.create(user);
        assertNotNull(result);

        verify(userRepository).saveAndFlush(any());
    }

    @Test
    void create_withId() {
        User user = new User();
        user.setId(1);
        user.setUserRole(UserRole.PATIENT);
        user.setGender(Gender.MALE);
        user.setFirstName("Test");
        user.setLastName("Test");
        
        User result = userService.create(user);
        assertNull(result);

        verifyNoInteractions(userRepository);
    }

    @Test
    void update() {
        User user = new User();
        user.setId(1);
        user.setUserRole(UserRole.PATIENT);
        user.setGender(Gender.MALE);
        user.setFirstName("Test");
        user.setLastName("Test");

        when(userRepository.saveAndFlush(any())).thenReturn(user);

        User result = userService.update(user);
        assertNotNull(result);

        verify(userRepository).saveAndFlush(any());
    }

    @Test
    void update_withoutOId() {
        User user = new User();
        user.setUserRole(UserRole.PATIENT);
        user.setGender(Gender.MALE);
        user.setFirstName("Test");
        user.setLastName("Test");

        User result = userService.update(user);
        assertNull(result);

        verifyNoInteractions(userRepository);
    }

    @Test
    void findById() {
        User user = new User();
        user.setId(1);
        user.setUserRole(UserRole.PATIENT);
        user.setGender(Gender.MALE);
        user.setFirstName("Test");
        user.setLastName("Test");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        User result = userService.findById(1);
        assertNotNull(result);

        verify(userRepository).findById(anyInt());
    }

    @Test
    void findById_entityNotFound() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        User result = userService.findById(1);
        assertNull(result);

        verify(userRepository).findById(anyInt());
    }

}