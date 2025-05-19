package com.example.demo.features.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EmailAlreadyInUseException;
import com.example.demo.exceptions.InvalidEmailFormatException;
import com.example.demo.exceptions.VeryWeakPasswordException;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(UserRegisterDTO userRegisterDTO) throws 
        EmailAlreadyInUseException, 
        InvalidEmailFormatException, 
        VeryWeakPasswordException 
    {
        
        if (
            !userRegisterDTO.email().contains("@") || 
            !userRegisterDTO.email().contains(".") 
        )
            throw new InvalidEmailFormatException("E-mail inválido!");
        
        if (this.userRepository.existsByEmail(userRegisterDTO.email()))
            throw new EmailAlreadyInUseException(
                "Este e-mail já está sendo usado!"
            );

        if (userRegisterDTO.password().contains(" "))
            throw new IllegalArgumentException(
                "A senha não pode conter espaços em branco!"
            );

        if (userRegisterDTO.password().length() < 8)
            throw new VeryWeakPasswordException(
                "A senha deve conter no mínimo 8 caracteres!"
            );

        if (userRegisterDTO.fullName().isBlank())
            throw new IllegalArgumentException(
                "O nome não pode ficar em branco!"
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
