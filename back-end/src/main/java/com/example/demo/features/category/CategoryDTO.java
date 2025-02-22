package com.example.demo.features.category;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO(
    UUID id,

    @NotBlank
    @Size(
        min = 1,
        max = 30,
        message = "O nome da categoria deve conter entre 1 e 30 caracteres."
    )
    String name,

    String imageLink
) {
    public Category convertToEntity() {
        return new Category(this.id, this.name, this.imageLink);
    }
}
