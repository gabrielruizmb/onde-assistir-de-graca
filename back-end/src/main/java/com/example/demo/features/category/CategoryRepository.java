package com.example.demo.features.category;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID>{
    
}
