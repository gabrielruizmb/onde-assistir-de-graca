package com.example.demo.features.film;

import java.nio.channels.Channel;
import java.util.List;
import java.util.UUID;
import java.util.Locale.Category;

import com.example.demo.features.genre.Genre;

public record FilmDTO(
    UUID id, 
    String title, 
    int year,
    String posterUrl,
    String description, 
    Category category,
    List<Genre> genres,
    List<Channel> channels) {
    
    public Film convertToEntity() {
        return new Film(
            id, 
            title, 
            year,
            posterUrl, 
            description, 
            category, 
            genres, 
            channels
        );
    }
}
