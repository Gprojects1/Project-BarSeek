package com.example.barseek_rating_mservice.repository

import com.example.barseek_rating_mservice.model.BarRating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BarRatingRepository : JpaRepository<BarRating, Long> {
    fun findByBarId(barId : Long) : BarRating?
}