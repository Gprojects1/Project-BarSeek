package com.example.barseek_profile_mservice.dto;


import com.example.barseek_profile_mservice.model.type.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateProfileRequest {

    private String email;

    private String firstName;

    private String secondName;

    private String phone;

    private LocalDateTime birthDate;

    private Sex sex;

}
