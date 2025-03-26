package com.example.demo.features.channel;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody 
                                                        ChannelDTO 
                                                        channelDTO) {
        return channelService.create(channelDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<HashMap<String, String>> put(
        @PathVariable UUID id, @RequestBody ChannelDTO channelDTO) {

        return channelService.update(id, channelDTO);
    }

    @GetMapping
    public ResponseEntity<List<ChannelDTO>> getAll() {
        return channelService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ChannelDTO> getById(@PathVariable UUID id) {
        return channelService.getById(id);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<ChannelDTO> deleteById(@PathVariable UUID id) {
        return channelService.deleteById(id);
    }
}
