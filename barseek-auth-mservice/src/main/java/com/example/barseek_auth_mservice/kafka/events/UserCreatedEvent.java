package com.example.barseek_auth_mservice.kafka.events;


import com.example.barseek_auth_mservice.model.type.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {
    private Long id;
    private String email;
    private Role role;
}
