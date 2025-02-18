package com.example.barseek_auth_mservice.model.entity;


import com.example.barseek_auth_mservice.model.type.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String pass;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
