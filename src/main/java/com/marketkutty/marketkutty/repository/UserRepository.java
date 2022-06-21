package com.marketkutty.marketkutty.repository;

import com.marketkutty.marketkutty.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByUsername (String username);
    Optional<User> findByEmail (String email);
    Optional<User> findByUsernameAndPassword (String username, String pw_sha256);
}
