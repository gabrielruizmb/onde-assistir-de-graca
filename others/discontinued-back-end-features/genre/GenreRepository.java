package com.example.demo.features.genre;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, UUID>{
    
}
