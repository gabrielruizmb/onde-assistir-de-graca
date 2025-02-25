package com.example.demo.features.category;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.features.ResponseDTO;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<ResponseDTO> create(CategoryDTO categoryDTO) {

        if (categoryDTO.name().isBlank() || categoryDTO.name().length() > 30) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDTO(
                "O nome da categoria deve conter entre 1 e 30 caracteres"
                )
            );
        }

        try {
            categoryRepository.save(categoryDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDTO("Já existe uma categoria com este nome")
            );
        }
    }

    public ResponseEntity<ResponseDTO> update(CategoryDTO categoryDTO, UUID id) {

        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO("Categoria não encontrada")
            );
        }

        if (categoryDTO.name().isBlank() || categoryDTO.name().length() > 30) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDTO(
                "O nome da categoria deve conter entre 1 e 30 caracteres"
                )
            );
        }

        try {
            categoryRepository.save(categoryDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDTO("Já existe uma categoria com este nome")
            );
        }
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

    public void delete(UUID id) {
        Assert.isTrue(categoryRepository.existsById(id), 
        "Registro não encontrado");

        categoryRepository.deleteById(id);
    }
}
