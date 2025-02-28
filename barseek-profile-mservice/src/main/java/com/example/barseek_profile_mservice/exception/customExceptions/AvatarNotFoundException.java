package com.example.barseek_profile_mservice.exception.customExceptions;

public class AvatarNotFoundException extends RuntimeException {
    public AvatarNotFoundException(String message) {
      super(message);
    }
}
