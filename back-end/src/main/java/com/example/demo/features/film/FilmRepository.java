package com.example.demo.features.film;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.features.category.Category;


public interface FilmRepository extends JpaRepository<Film, UUID>{
    public Page<Film> findByCategory(Category category, PageRequest pageRequest);
}
