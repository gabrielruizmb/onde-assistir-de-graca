package com.example.demo.features.film;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.features.category.Category;


public interface FilmRepository extends JpaRepository<Film, UUID>{
    public List<Film> findByCategory(Category category);
}
