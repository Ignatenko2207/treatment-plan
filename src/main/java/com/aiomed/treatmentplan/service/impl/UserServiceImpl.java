package com.aiomed.treatmentplan.service.impl;

import com.aiomed.treatmentplan.model.User;
import com.aiomed.treatmentplan.repository.UserRepository;
import com.aiomed.treatmentplan.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User create(User user) {
        try {
            if (Objects.isNull(user.getId())) {
                return userRepository.saveAndFlush(user);
            }
            log.warn("User id can't be null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public User update(User user) {
        try {
            if (Objects.nonNull(user.getId())) {
                return userRepository.saveAndFlush(user);
            }
            log.warn("User id can't be not null");
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        try {
            return userRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
