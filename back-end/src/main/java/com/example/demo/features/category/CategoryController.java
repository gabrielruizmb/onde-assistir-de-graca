package com.example.demo.features.category;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Validated 
            CategoryDTO categoryDTO) {
        try {
            this.categoryService.create(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                "Este nome de categoria já existe"
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody @Validated 
    CategoryDTO categoryDTO, @PathVariable UUID id) {
        try {
            this.categoryService.update(categoryDTO, id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                exception.getMessage()
            );
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                this.categoryService.getById(id)
            );
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
            categoryService.getAll()
        );
    }

    // @DeleteMapping
    // public ResponseEntity<String> delete(@PathVariable UUID id) {
    //     try {
    //         this.categoryService.delete(id);
    //         return ResponseEntity.status(HttpStatus)
    //     }
    // }
}
