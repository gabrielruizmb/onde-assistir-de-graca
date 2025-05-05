package com.example.demo.features.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    CategoryService categoryService;
    
    @Test
    @DisplayName("Cenário 001 - Testando método getAll")
    void scenario001() {
        ResponseEntity<List<CategoryDTO>> list = categoryService.getAll();

        assertEquals(HttpStatus.OK, list.getStatusCode());
    }
}
