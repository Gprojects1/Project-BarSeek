package com.example.barseek_auth_mservice.kafka.events;

import lombok.Data;

@Data
public class ProfileDeletedEvent {
    private Long userId;
    private String email;
}
