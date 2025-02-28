package com.example.barseek_profile_mservice.kafka.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileDeletedEvent {
    private Long userId;
    private String email;
}
