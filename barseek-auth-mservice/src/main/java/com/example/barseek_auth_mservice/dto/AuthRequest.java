package com.example.barseek_auth_mservice.dto;


import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
