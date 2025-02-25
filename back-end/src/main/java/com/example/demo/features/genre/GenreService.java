package com.example.demo.features.genre;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.features.ResponseDTO;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public ResponseEntity<ResponseDTO> create(GenreDTO genreDTO) {

        if (genreDTO.name().isBlank() || genreDTO.name().length() > 30) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDTO(
                    "O nome do gênero deve ter entre 1 e 30 caracteres."
                )
            );
        }

        try {
            genreRepository.save(genreDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDTO("Já existe um gênero com este nome")
            );
        }
    }

    public ResponseEntity<ResponseDTO> update(GenreDTO genreDTO, UUID id) {

        if (!genreRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDTO("Gênero não encontrado")
            );
        }

        if (genreDTO.name().isBlank() || genreDTO.name().length() > 30) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDTO(
                    "O nome do gênero deve ter entre 1 e 30 caracteres."
                )
            );
        }

        try {
            genreRepository.save(genreDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDTO("Já existe um gênero com este nome")
            );
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
            GenreDTO genreDTO = genreRepository.findById(id).get().convertToDTO();
            return ResponseEntity.status(HttpStatus.OK).body(genreDTO);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<ResponseDTO> deleteById(UUID id) {
        
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        genreRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
