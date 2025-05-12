package com.example.demo.features.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID>{
    public Optional<User> findByEmail(String email);
    public boolean existsByEmail(String email);
}
