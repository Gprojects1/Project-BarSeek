package com.example.barseek_rating_mservice.repository

import com.example.barseek_rating_mservice.model.DrinkRating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrinkRatingRepository : JpaRepository<DrinkRating, Long> {
    fun findByDrinkId(drinkId : Long) : DrinkRating?
}