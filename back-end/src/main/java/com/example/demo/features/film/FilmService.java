package com.example.demo.features.film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.features.category.Category;
import com.example.demo.features.category.CategoryRepository;

@Service
public class FilmService {
    
    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;

    public FilmService(FilmRepository filmRepository, 
                       CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<HashMap<String, String>> create(FilmDTO filmDTO) {
                
        HashMap<String, String> response = new HashMap<>();

        if (filmDTO.title() == null) {
            response.put("title", "O título não pode ser nulo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
  
        if (filmDTO.title().isBlank()) {
            response.put("title", "O título não pode ficar em branco");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (filmDTO.title().length() > 50) {
            response.put("title", "O título pode ter no máx. 50 caracteres");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (filmDTO.channels().isEmpty()) {
            response.put("channels", "O filme deve passar em ao menos 1 canal");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            filmRepository.save(filmDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            response.put("title", "Este título já existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<HashMap<String, String>> update(UUID id, 
                                                          FilmDTO filmDTO) {

        if (!filmRepository.existsById(id)) 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return create(filmDTO);
    }

    public ResponseEntity<List<FilmDTO>> getAll() {
        List<FilmDTO> filmDTOs = new ArrayList<>();
        List<Film> films = filmRepository.findAll();

        for (Film film : films) {
            filmDTOs.add(film.convertToDTO());
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmDTOs);
    }

    public ResponseEntity<List<FilmDTO>> getByCategory(UUID id) {
        Category category = categoryRepository.findById(id).get();
        
        List<FilmDTO> filmDTOs = new ArrayList<>();
        List<Film> films = filmRepository.findByCategory(category);

        for (Film film : films) {
            filmDTOs.add(film.convertToDTO());
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmDTOs);
    }

    public ResponseEntity<FilmDTO> getById(UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                filmRepository.findById(id).get().convertToDTO()
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<FilmDTO> deleteById(UUID id) {

        if(!filmRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        filmRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

    }
}
