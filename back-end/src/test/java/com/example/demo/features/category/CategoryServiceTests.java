package com.example.demo.features.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
    @DisplayName("Cenário 001 - Testando método getAll")
    void scenario001() {

        List<Category> categories = new ArrayList<>();

        when(categoryRepository.findAll()).thenReturn(categories);

        ResponseEntity<List<CategoryDTO>> list = categoryService.getAll();

        assertEquals(HttpStatus.OK, list.getStatusCode());
    }
}
