package com.example.barseek_auth_mservice.exception.customExceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {
         super(message);
    }
}
