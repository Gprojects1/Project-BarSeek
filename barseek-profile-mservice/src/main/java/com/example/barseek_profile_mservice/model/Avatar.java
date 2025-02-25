package com.example.barseek_profile_mservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "file_path")
    private String filePath;

    @OneToOne(mappedBy = "avatar")
    private UserProfile userProfile;

}
