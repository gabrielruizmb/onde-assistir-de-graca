package com.example.demo.features.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<HashMap<String, String>> create(CategoryDTO 
                                                          categoryDTO) {

        HashMap<String, String> response = new HashMap<>();

        if (categoryDTO.name() == null) {
            response.put("nome", "O nome não pode ser nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        if (categoryDTO.name().isBlank()) {
            response.put("nome", "O nome não pode ficar em branco");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        if (categoryDTO.name().length() > 30) {
            response.put("nome", "O nome pode ter no máx. 30 caracteres");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        try {

            categoryRepository.save(categoryDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        
        } catch (Exception exception) {

            response.put("nome", "Já existe uma categoria com este nome");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<HashMap<String, String>> update(
        CategoryDTO categoryDTO, UUID id) {

        HashMap<String, String> response = new HashMap<>();

        if (!categoryRepository.existsById(id)) {
            response.put("id", "Não existe uma categoria com este id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
            
        if (categoryDTO.name() == null) {
            response.put("nome", "O nome não pode ser nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        if (categoryDTO.name().isBlank()) {
            response.put("nome", "O nome não pode ficar em branco");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        if (categoryDTO.name().length() > 30) {
            response.put("nome", "O nome pode ter no máx. 30 caracteres");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }   

        try {
            categoryRepository.save(categoryDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            response.put("nome", "Já existe uma categoria com este nome");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<CategoryDTO> getById(UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                categoryRepository.findById(id).get().convertToDTO()
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTOs = new ArrayList<>();
        
        for(Category category : categories) {
            categoriesDTOs.add(category.convertToDTO());
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoriesDTOs);
    }

    public ResponseEntity<CategoryDTO> delete(UUID id) {

        if (!categoryRepository.existsById(id)) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        
        categoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
