package com.example.barseek_bar_mservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bars")
@Data
@AllArgsConstructor

public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "creation_date")
    private LocalDateTime createdAt;
    @Column(name = "last_update")
    private LocalDateTime updatedAt;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "type")
    private BarType type;

    @OneToMany(mappedBy = "bar",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Drink> drinks;

}
