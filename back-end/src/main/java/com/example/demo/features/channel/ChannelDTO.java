package com.example.demo.features.channel;

import java.util.UUID;

public record ChannelDTO(UUID id, String url, String name, String imageUrl) {
    public Channel convertToEntity() {
        return new Channel(id, url, name, imageUrl);
    }
}
