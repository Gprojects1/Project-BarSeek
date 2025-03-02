package com.example.barseek_rating_mservice.service

import com.example.barseek_rating_mservice.exception.custom.BarNotFoundException
import com.example.barseek_rating_mservice.exception.custom.DrinkNotFoundException
import com.example.barseek_rating_mservice.model.BarRating
import com.example.barseek_rating_mservice.model.DrinkRating
import com.example.barseek_rating_mservice.repository.BarRatingRepository
import com.example.barseek_rating_mservice.repository.DrinkRatingRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class RatingService(
    private val barRatingRepository: BarRatingRepository,
    private val drinkRatingRepository: DrinkRatingRepository
) {

    @Transactional
    fun getBarRating(barId : Long) : BarRating {
        val rating : BarRating? = barRatingRepository.findByBarId(barId)
        return rating ?: throw BarNotFoundException("no bar with $barId id!")
    }

    @Transactional
    fun getDrinkRating(drinkId : Long) : DrinkRating {
        val rating : DrinkRating? = drinkRatingRepository.findByDrinkId(drinkId)
        return rating ?: throw DrinkNotFoundException("no bar with $drinkId id!")
    }
}