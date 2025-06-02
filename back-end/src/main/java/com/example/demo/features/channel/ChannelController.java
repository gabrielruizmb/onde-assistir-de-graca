package com.example.demo.features.channel;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.GenericResponseDTO;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/channels")
public class ChannelController {
    
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HashMap<String, String>> post(@RequestBody 
                                                        ChannelDTO 
                                                        channelDTO) {
        return channelService.create(channelDTO);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HashMap<String, String>> put(
        @PathVariable UUID id, @RequestBody ChannelDTO channelDTO) {

        return channelService.update(id, channelDTO);
    }

    @GetMapping
    public ResponseEntity<List<ChannelDTO>> getAll() {
        return channelService.getAll();
    }

    
    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ChannelDTO> getById(@PathVariable UUID id) {
        return channelService.getById(id);
    }
    
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GenericResponseDTO> deleteById(@PathVariable UUID id) {
        return channelService.deleteById(id);
    }
}
