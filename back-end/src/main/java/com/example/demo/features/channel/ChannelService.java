package com.example.demo.features.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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

    public ResponseEntity<HashMap<String, String>> update(
        UUID id, ChannelDTO channelDTO) {

        if (!channelRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            HashMap<String, String> response = new HashMap<>();
            response.put("nome", "Este nome de canal já está sendo usado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<List<ChannelDTO>> getAll() {
        List<ChannelDTO> channelDTOs = new ArrayList<>();
        List<Channel> channels = channelRepository.findAll();

        for (Channel channel : channels) {
            channelDTOs.add(channel.convertToDTO());
        }

        return ResponseEntity.status(HttpStatus.OK).body(channelDTOs);
    }

    public ResponseEntity<ChannelDTO> getById(UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                channelRepository.findById(id).get().convertToDTO()
            );
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } 
    }

    public ResponseEntity<ChannelDTO> deleteById(UUID id) {

        if (!channelRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        
        channelRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
