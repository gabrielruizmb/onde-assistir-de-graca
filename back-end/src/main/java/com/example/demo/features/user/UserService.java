package com.example.demo.features.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(
        UserRegisterDTO userRegisterDTO
    ) throws EmailAlreadyInUseException, InvalidEmailFormatException {
        
        if (
            !userRegisterDTO.email().contains("@") || 
            !userRegisterDTO.email().contains(".") 
        )
            throw new InvalidEmailFormatException("E-mail inválido!");
        
        if (this.userRepository.existsByEmail(userRegisterDTO.email()))
            throw new EmailAlreadyInUseException(
                "Este e-mail já está sendo usado!"
            );

        User user = new User(
            null, 
            userRegisterDTO.email(), 
            new BCryptPasswordEncoder().encode(userRegisterDTO.password()), 
            userRegisterDTO.fullName(), 
            "ROLE_COLLABORATOR"
        );

        userRepository.save(user);
    }
}
