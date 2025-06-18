package com.example.demo.features.film;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<HashMap<String, String>> put(
        @PathVariable UUID id, @RequestBody FilmDTO filmDTO
    ) {
        return filmService.update(id, filmDTO);
    }

    @GetMapping("{pageNumber}/{quantityPerPage}")
    public ResponseEntity<Page<Film>> getAll(
        @PathVariable int pageNumber, @PathVariable int quantityPerPage
    ) {
        return filmService.getAll(pageNumber, quantityPerPage);
    }

    @GetMapping("by-category/{id}/{pageNumber}/{quantityPerPage}")
    public ResponseEntity<Page<Film>> getByCategory(
        @PathVariable UUID id,
        @PathVariable int pageNumber, 
        @PathVariable int quantityPerPage
    ) {
        return filmService.getByCategory(id, pageNumber, quantityPerPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<FilmDTO> getById(@PathVariable UUID id) {
        return filmService.getById(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COLLABORATOR')")
    public ResponseEntity<FilmDTO> deleteById(@PathVariable UUID id) {
        return filmService.deleteById(id);
    }
}
