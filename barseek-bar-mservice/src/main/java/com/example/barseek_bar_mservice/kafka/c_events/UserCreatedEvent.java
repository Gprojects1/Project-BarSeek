package com.example.barseek_bar_mservice.kafka.c_events;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserCreatedEvent {
    private Long userId;
    private String email;
    private String role;
}
