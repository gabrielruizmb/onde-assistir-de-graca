package com.example.demo.features.category;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<HashMap<String, String>> create(
        @RequestBody CategoryDTO categoryDTO) {
            
        return this.categoryService.create(categoryDTO);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HashMap<String, String>> update(
        @RequestBody CategoryDTO categoryDTO,
        @PathVariable UUID id) {
        return this.categoryService.update(categoryDTO, id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTO> getById(@PathVariable UUID id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return categoryService.getAll();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTO> delete(@PathVariable UUID id) {
        return categoryService.delete(id);
    }
}
