package com.example.demo.features.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, UUID>{
    public boolean existsById(UUID id);
    public boolean existsByName(String name);
    public Optional<Category> findByName(String name);
}
