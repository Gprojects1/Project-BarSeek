package com.example.barseek_rating_mservice.kafka

import com.example.barseek_rating_mservice.dto.BarReviewCreatedEvent
import com.example.barseek_rating_mservice.dto.DrinkReviewCreatedEvent
import com.example.barseek_rating_mservice.service.RatingService
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
@KafkaListener(topics = ["review.events"], groupId = "rating-service-group")
class KafkaConsumerService(private val ratingService: RatingService) {

    @KafkaHandler
    fun handleBarReviewCreatedEvent(event : BarReviewCreatedEvent) {
        ratingService.updateBarRating(event.barId,event.score)
    }

    @KafkaHandler
    fun handleDrinkReviewCreatedEvent(event : DrinkReviewCreatedEvent) {
        ratingService.updateDrinkRating(event.drinkId,event.score)
    }

}