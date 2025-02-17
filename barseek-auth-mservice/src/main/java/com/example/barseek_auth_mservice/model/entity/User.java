package com.example.barseek_auth_mservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    Long id;
}
