package com.example.demo.features.genre;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> create(@RequestBody 
                                                          GenreDTO genreDTO) {
        return genreService.create(genreDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<HashMap<String, String>> update(
        @RequestBody GenreDTO genreDTO,
        @PathVariable UUID id) {
        return genreService.update(genreDTO, id);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {
        return genreService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GenreDTO> getById(@PathVariable UUID id) {
        return genreService.getById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenreDTO> deleteById(@PathVariable UUID id) {
        return genreService.deleteById(id);
    }
}
