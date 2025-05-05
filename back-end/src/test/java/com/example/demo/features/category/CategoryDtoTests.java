package com.example.demo.features.category;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryDtoTests {
    
    @Test
    @DisplayName("Cenário 001 - Testando método convertToEntity")
    void scenario001() {

        CategoryDTO categoryDTO = new CategoryDTO(null, null, null);
        Category category = categoryDTO.convertToEntity();

        assertNotNull(category);
    }
}
