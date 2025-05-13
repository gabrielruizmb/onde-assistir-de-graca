package com.example.demo.features.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserController(
        UserService userService, 
        TokenService tokenService, 
        AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<GenericResponseDTO> signIn(
        @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequestDTO.email(), loginRequestDTO.password()
            );
    
            var auth = authenticationManager.authenticate(usernamePassword);
    
            var token = tokenService.generateToken(auth);
    
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(new GenericResponseDTO(token));
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(
                        new GenericResponseDTO("Usuário ou senha incorretos")
                    );
        }
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GenericResponseDTO> signUp(
        @RequestBody UserRegisterDTO userRegisterDTO
    ) {

        try {
            userService.create(userRegisterDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new GenericResponseDTO(exception.getMessage()));
        }
    }
}
