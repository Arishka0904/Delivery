package com.delivery.repository;

import com.delivery.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByActivationCode(String code);
    User findByEmail(String email);
}
