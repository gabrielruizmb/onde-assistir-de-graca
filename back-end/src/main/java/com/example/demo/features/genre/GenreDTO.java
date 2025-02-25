package com.example.demo.features.genre;

import java.util.UUID;

public record GenreDTO(UUID id, String name, String imageUrl) {
    public Genre convertToEntity() {
        return new Genre(id, name, imageUrl);
    }
}
