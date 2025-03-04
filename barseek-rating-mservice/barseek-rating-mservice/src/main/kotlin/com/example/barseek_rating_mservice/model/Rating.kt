package com.example.barseek_rating_mservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
abstract class Rating (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long ? = null,

    @Column(name = "score")
    var score : Double = 0.0,

    @Column(name = "review_count")
    var reviewCount : Int = 0
)
