package com.example.barseek_rating_mservice.model

import jakarta.persistence.*

@Entity
data class BarRating (
    @Column(name = "bar_id")
    val barId : Long
) : Rating()


@Entity
data class DrinkRating (
    @Column(name = "drink_id")
    val drinkId : Long
) : Rating()


