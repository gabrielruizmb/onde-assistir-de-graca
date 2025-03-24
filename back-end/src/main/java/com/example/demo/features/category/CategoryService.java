package com.example.demo.features.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.features.ResponseDTO;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<HashMap<String, String>> create(CategoryDTO 
                                                          categoryDTO) {

        if (categoryDTO.name().isBlank() || categoryDTO.name().length() > 30) {

            HashMap<String, String> response = new HashMap<>();
            response.put("nome", "O nome deve conter entre 1 e 30 caracteres");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        try {

            categoryRepository.save(categoryDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        
        } catch (Exception exception) {

            HashMap<String, String> response = new HashMap<>();
            response.put("nome", "Já existe uma categoria com este nome");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
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

    public ResponseEntity<CategoryDTO> getById(UUID id) {
        try {
            CategoryDTO categoryDTO = categoryRepository.findById(id).get().convertToDTO();
            return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTOs = new ArrayList<>();
        
        for(Category category : categories) {
            categoriesDTOs.add(category.convertToDTO());
        }

        return categoriesDTOs;
    }

    public ResponseEntity<ResponseDTO> delete(UUID id) {

        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        categoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
