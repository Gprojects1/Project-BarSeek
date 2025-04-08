package com.example.barseek_profile_mservice.exception.customExceptions;

public class FileProcessingFailedException extends RuntimeException {
    public FileProcessingFailedException(String message) {
        super(message);
    }
}
