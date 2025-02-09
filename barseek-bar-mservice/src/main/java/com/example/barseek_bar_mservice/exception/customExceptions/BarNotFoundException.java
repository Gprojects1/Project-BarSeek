package com.example.barseek_bar_mservice.exception.customExceptions;

public class BarNotFoundException extends RuntimeException {
    public BarNotFoundException(String message) {
        super(message);
    }
}
