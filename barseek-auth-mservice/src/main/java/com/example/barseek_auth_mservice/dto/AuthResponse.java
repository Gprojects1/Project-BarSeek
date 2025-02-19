package com.example.barseek_auth_mservice.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
}
