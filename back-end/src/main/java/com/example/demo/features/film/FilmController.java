package com.example.demo.features.film;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/films")
public class FilmController {
    
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<HashMap<String, String>> post(@RequestBody 
                                                        FilmDTO filmDTO) {
        return filmService.create(filmDTO);
    }
    
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HashMap<String, String>> put(
        @PathVariable UUID id, @RequestBody FilmDTO filmDTO
    ) {
        return filmService.update(id, filmDTO);
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAll() {
        return filmService.getAll();
    }

    @GetMapping("by-category/{id}")
    public ResponseEntity<List<FilmDTO>> getByCategory(@PathVariable UUID id) {
        return filmService.getByCategory(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FilmDTO> getById(@PathVariable UUID id) {
        return filmService.getById(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<FilmDTO> deleteById(@PathVariable UUID id) {
        return filmService.deleteById(id);
    }
}
