package com.example.barseek_auth_mservice.kafka.events;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileUpdatedEvent {
    private Long userId;
    private String email;
}
