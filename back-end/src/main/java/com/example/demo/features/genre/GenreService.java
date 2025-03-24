package com.example.demo.features.genre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public ResponseEntity<HashMap<String, String>> create(GenreDTO genreDTO) {

        if (genreDTO.name().isBlank() || genreDTO.name().length() > 30) {

            HashMap<String, String> response = new HashMap<String, String>();

            if (genreDTO.name().isBlank())
                response.put("nome", "O nome não pode ficar em branco");

            if (genreDTO.name().length() > 30)
                response.put("nome", "O nome pode ter no máx. 30 caracteres");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        try {
            genreRepository.save(genreDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);

        } catch (Exception exception) {

            HashMap<String, String> response = new HashMap<String, String>();
            response.put("name", "Já existe um gênero com este nome");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<HashMap<String, String>> update(GenreDTO genreDTO, 
                                                          UUID id) {

        if (!genreRepository.existsById(id)) {

            HashMap<String, String> response = new HashMap<String, String>();
            response.put("id", "Não existe um Gênero com este id");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (genreDTO.name().isBlank() || genreDTO.name().length() > 30) {
            HashMap<String, String> response = new HashMap<String, String>();

            if (genreDTO.name().isBlank())
                response.put("nome", "O nome não pode ficar em branco");

            if (genreDTO.name().length() > 30)
                response.put("nome", "O nome pode ter no máx. 30 caracteres");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                response
            );
        }

        try {
            genreRepository.save(genreDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("name", "Já existe um gênero com este nome");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<List<GenreDTO>> getAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDTO> genreDTOs = new ArrayList<>();

        for(Genre genre : genres) {
            genreDTOs.add(genre.convertToDTO());
        }

        return ResponseEntity.status(HttpStatus.OK).body(genreDTOs);
    }

    public ResponseEntity<GenreDTO> getById(UUID id) {
        try {
            GenreDTO genreDTO = genreRepository.findById(id).get()
                                                .convertToDTO();
            return ResponseEntity.status(HttpStatus.OK).body(genreDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<GenreDTO> deleteById(UUID id) {
        
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        genreRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
