package com.example.demo.features.category;

import java.util.UUID;

public record CategoryDTO(UUID id, String name, String imageUrl
) {
    public Category convertToEntity() {
        return new Category(this.id, this.name, this.imageUrl);
    }
}
