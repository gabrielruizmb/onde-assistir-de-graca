package com.example.demo.features.channel;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, UUID>{
    
}
