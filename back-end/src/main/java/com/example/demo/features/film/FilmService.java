package com.example.demo.features.film;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public ResponseEntity<HashMap<String, String>> create(FilmDTO filmDTO) {

        if (filmDTO.title().isBlank() || filmDTO.title().length() > 50) {
            HashMap<String, String> response = new HashMap<>();

            if (filmDTO.title().isBlank())
                response.put("title", "O título não pode ficar em branco");

            if (filmDTO.title().length() > 50)
                response
                 .put("title", "O título pode ter no máx. 50 caracteres");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                  .body(response);
        }

        try {
            filmRepository.save(filmDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            HashMap<String, String> response = new HashMap<>();
            response.put("title", "Este título já existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
