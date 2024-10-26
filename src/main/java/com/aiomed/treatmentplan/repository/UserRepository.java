package com.aiomed.treatmentplan.repository;

import com.aiomed.treatmentplan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
