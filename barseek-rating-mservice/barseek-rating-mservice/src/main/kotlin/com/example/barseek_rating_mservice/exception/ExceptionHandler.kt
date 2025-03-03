package com.example.barseek_rating_mservice.exception

import com.example.barseek_rating_mservice.exception.custom.BarNotFoundException
import com.example.barseek_rating_mservice.exception.custom.DrinkNotFoundException
import com.example.barseek_rating_mservice.exception.custom.InvalidDataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BarNotFoundException :: class)
    fun handleBarNotFound(ex : BarNotFoundException) : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(DrinkNotFoundException :: class)
    fun handleDrinkNotFound(ex : DrinkNotFoundException) : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(InvalidDataException :: class)
    fun handleInvalidData(ex : InvalidDataException) : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(Exception :: class)
    fun handleInternal(ex : Exception) : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong!....")
    }
}