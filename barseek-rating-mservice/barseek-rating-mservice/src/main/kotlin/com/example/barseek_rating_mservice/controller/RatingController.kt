package com.example.barseek_rating_mservice.controller

import com.example.barseek_rating_mservice.model.BarRating
import com.example.barseek_rating_mservice.model.DrinkRating
import com.example.barseek_rating_mservice.service.RatingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rating-service-api/v1/ratings")
class RatingController(private val ratingService: RatingService) {

    @GetMapping("drinks/{drinkId}")
    fun getDrinkRating(@PathVariable("drinkId") id : Long) : ResponseEntity<DrinkRating> {
        val rating : DrinkRating = ratingService.getDrinkRating(id)
        return ResponseEntity.ok(rating)
    }

    @GetMapping("bars/{barId}")
    fun getBarRating(@PathVariable("barId") id : Long) : ResponseEntity<BarRating> {
        val rating : BarRating = ratingService.getBarRating(id)
        return ResponseEntity.ok(rating)
    }

}