package com.example.demo.features.category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void create(CategoryDTO categoryDTO) {
        this.categoryRepository.save(categoryDTO.convertToEntity());
    }

    public void update(CategoryDTO categoryDTO) {         
        this.categoryRepository.save(categoryDTO.convertToEntity());
    }

    public CategoryDTO getById(UUID id) {
        CategoryDTO categoryDTO = categoryRepository.findById(id).get().convertToDTO();
        return categoryDTO;
    }

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTOs = new ArrayList<>();
        
        for(Category category : categories) {
            categoriesDTOs.add(category.convertToDTO());
        }

        return categoriesDTOs;
    }
}
