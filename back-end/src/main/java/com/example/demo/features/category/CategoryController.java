package com.example.demo.features.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Category category) {
        try {
            this.categoryService.create(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                exception.getMessage()
            );
        }
    }
}
