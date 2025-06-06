package com.example.demo.features.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.features.GenericResponseDTO;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }
    
    public ResponseEntity<HashMap<String, String>> create(ChannelDTO 
                                                          channelDTO) {

        HashMap<String, String> response = new HashMap<>();

        if (channelDTO.name() == null) {
            response.put("name", "O nome não pode ser nulo");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
        }

        if (channelDTO.name().isBlank()) {
            response.put("name", "O nome não pode ficar em branco");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
        }

        if (channelDTO.name().length() > 30) {
            response.put("name", "O nome pode ter no máx. 30 caracteres");
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
        }

        try {
            channelRepository.save(channelDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (Exception exception) {
            response.put("name", "Este nome de canal já está sendo usado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public ResponseEntity<HashMap<String, String>> update(
        UUID id, ChannelDTO channelDTO) {

        if (!channelRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        HashMap<String, String> response = new HashMap<>();

        if (channelDTO.name() == null) {
            response.put("name", "O nome não pode ser nulo");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
        }

        if (channelDTO.name().isBlank()) {
            response.put("name", "O nome não pode ficar em branco");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(response);
        }

        if (channelDTO.name().length() > 30) {
            response.put("name", "O nome pode ter no máx. 30 caracteres");
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
        }

        try {
            channelRepository.save(channelDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception exception) {
            response.put("name", "Este nome de canal já está sendo usado");
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

    public ResponseEntity<GenericResponseDTO> deleteById(UUID id) {

        if (!channelRepository.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new GenericResponseDTO("Canal não encontrado")
            );
        
        try {
            channelRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new GenericResponseDTO("Canal excluído")
            );
        } catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GenericResponseDTO(
                    "Este canal está vínculado a um ou mais filmes, desvincule-os para poder excluí-lo"
                )
            );
        }
    }
}
