package com.example.demo.features.channel;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
    
    public ResponseEntity<HashMap<String, String>> create(ChannelDTO 
                                                          channelDTO) {

        if (channelDTO.name().isBlank() || channelDTO.name().length() > 30) {
            HashMap<String, String> response = new HashMap<>();

            if (channelDTO.name().isBlank())
                response.put("nome", "O nome não pode ficar em branco");

            if (channelDTO.name().length() > 30)
                response.put("nome", "O não pode ter no máx. 30 caracteres");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                  .body(response);
        }

        try {
            channelRepository.save(channelDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            HashMap<String, String> response = new HashMap<>();
            response.put("nome", "Este nome de canal já está sendo usado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
