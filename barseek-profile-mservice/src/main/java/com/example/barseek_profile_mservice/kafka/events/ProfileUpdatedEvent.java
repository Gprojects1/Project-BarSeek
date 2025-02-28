package com.example.barseek_profile_mservice.kafka.events;

import com.example.barseek_profile_mservice.model.type.Sex;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProfileUpdatedEvent {
    private Long userId;
    private String email;
    private String firstName;
    private String secondName;
    private String phone;
    private LocalDateTime birthDate;
}
