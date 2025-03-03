package com.example.barseek_rating_mservice.dto

data class BarReviewCreatedEvent(
    val barId : Long,
    val score : Double
)

data class DrinkReviewCreatedEvent(
    val drinkId : Long,
    val score : Double
)
