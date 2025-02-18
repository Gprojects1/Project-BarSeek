package com.example.barseek_bar_mservice.model.entity;


import com.example.barseek_bar_mservice.model.types.DrinkSaleStatus;
import com.example.barseek_bar_mservice.model.types.DrinkType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "drinks")
@Data
@AllArgsConstructor

public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "information")
    private String info;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DrinkSaleStatus status;

    @Column(name = "type")
    private DrinkType type;

    @Column(name = "degree")
    private Integer degree;

    @Column(name = "creation_date")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;
}
