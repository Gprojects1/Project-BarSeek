package com.example.barseek_rating_mservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class BarRating (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long ? = null,

    @Column(name = "score")
    val score : Double = 0.0,

    @Column(name = "review_count")
    val reviewCount : Int = 0,

    @Column(name = "bar_id")
    val barId : Long
)


@Entity
data class DrinkRating (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long ? = null,

    @Column(name = "score")
    val score : Double = 0.0,

    @Column(name = "review_count")
    val reviewCount : Int = 0,

    @Column(name = "drink_id")
    val drinkId : Long
)
