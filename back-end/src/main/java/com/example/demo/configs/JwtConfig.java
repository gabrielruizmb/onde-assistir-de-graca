package com.example.demo.configs;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;

@Configuration
public class JwtConfig {
    
    private String secret = "T3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHTS1S013ST3RC3SR3PUSEHT";
    
    private String algorithm = "HS256";
    

    public SecretKey getSecretKey() {

        var key = new OctetSequenceKey.Builder(secret.getBytes())
                                      .algorithm(new JWSAlgorithm(algorithm))
                                      .build();
                            
        return key.toSecretKey();
    }

    public JWSAlgorithm getAlgorithm() {
        return new JWSAlgorithm(algorithm);
    }
}
