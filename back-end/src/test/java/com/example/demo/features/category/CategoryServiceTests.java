package com.example.demo.features.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    CategoryService categoryService;
    
    @MockitoBean
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Cenário 001 - Teste unitário - getAll")
    void scenario001() {

        List<Category> categories = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(categories);

        ResponseEntity<List<CategoryDTO>> list = categoryService.getAll();

        assertEquals(HttpStatus.OK, list.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 002 - Teste unitário - getById com id válido")
    void scenario002() {
         
        UUID id = new UUID(0, 0);
        Category category = new Category(null, null, null);
        Optional<Category> optionalCategory = Optional.of(category);

        when(categoryRepository.findById(id)).thenReturn(optionalCategory);

        categoryService.getById(id);
    }

    @Test
    @DisplayName("Cenário 003 - Teste unitário - getById com id inválido")
    void scenario003() {

        when(categoryRepository.findById(null)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<?> response = categoryService.getById(null);

        assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
        assertNull(response.getBody());
    }
}
