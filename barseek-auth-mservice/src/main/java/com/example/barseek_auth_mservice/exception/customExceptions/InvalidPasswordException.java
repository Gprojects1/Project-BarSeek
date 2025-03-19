package com.example.barseek_auth_mservice.exception.customExceptions;

public class InvalidPasswordException extends RuntimeException{
    InvalidPasswordException(String message) {
        super(message);
    }
}
