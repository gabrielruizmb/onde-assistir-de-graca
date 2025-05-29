package com.example.demo.features.user;

import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String email,
    String fullName,
    String role
) {
    
}
