package com.example.demo.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.features.user.User;

@Service
public class TokenService {
    
    String secret = "T3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHT";

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return "";
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusHours(1)
                            .toInstant(ZoneOffset.of("-03:00"));
    }
}
