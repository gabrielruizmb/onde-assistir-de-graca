package com.example.demo.features.film;

import java.util.List;
import java.util.UUID;

import com.example.demo.features.channel.Channel;
import com.example.demo.features.category.Category;

public record FilmDTO(
    UUID id, 
    String title, 
    int year,
    String posterUrl,
    String description,
    Category category,
    List<Channel> channels
) {
    
    public Film convertToEntity() {
        return new Film(
            id, 
            title, 
            year,
            posterUrl, 
            description,
            category,
            channels
        );
    }
}
