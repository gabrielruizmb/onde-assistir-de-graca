package com.example.demo.features.user;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String email,
    String fullName,
    List<String> roles
) {
    
}
