package com.example.demo.features.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configs.TokenService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
        @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
            loginRequestDTO.email(), loginRequestDTO.password()
        );

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken(auth);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new LoginResponseDTO(token));
    }
}
