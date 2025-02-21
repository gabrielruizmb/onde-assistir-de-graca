package com.example.demo.features.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException(
                "Este nome de categoria já está sendo usado!"
            );
        }
        this.categoryRepository.save(category);
    }
}
