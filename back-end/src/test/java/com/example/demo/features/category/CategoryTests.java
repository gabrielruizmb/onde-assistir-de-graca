package com.example.demo.features.category;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryTests {
    
    @Test
    @DisplayName("Cenário 001 - Testando método convertToDTO")
    void scenario001() {
        
        Category category = new Category(null, null, null);
        CategoryDTO categoryDTO = category.convertToDTO();

        assertNotNull(categoryDTO);
    }
}
