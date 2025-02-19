package com.example.barseek_auth_mservice.dto;

import com.example.barseek_auth_mservice.model.type.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
