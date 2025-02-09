package com.example.barseek_bar_mservice.exception.customExceptions;

public class DrinkNotFoundException extends RuntimeException {
    public DrinkNotFoundException(String message) {
        super(message);
    }
}
