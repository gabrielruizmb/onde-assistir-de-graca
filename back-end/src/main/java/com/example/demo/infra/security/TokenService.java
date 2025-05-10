package com.example.demo.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.features.user.User;

@Service
public class TokenService {
    
    String secret = "T3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHT";

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                              .withIssuer("onde_assistir_de_graca")
                              .withSubject(user.getEmail())
                              .withExpiresAt(generateExpirationTime())
                              .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token: ", exception);
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                      .withIssuer("onde_assistir_de_graca")
                      .build()
                      .verify(token)
                      .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationTime() {
        
        return LocalDateTime.now().plusHours(1)
                            .toInstant(ZoneOffset.of("-03:00"));
    }
}
