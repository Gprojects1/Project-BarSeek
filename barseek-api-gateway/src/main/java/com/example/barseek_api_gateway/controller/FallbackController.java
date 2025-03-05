package com.example.barseek_api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/bar")
    public ResponseEntity<String> barServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Bar service is UNAVAILABLE, try again later");
    }

    @GetMapping("/auth")
    public ResponseEntity<String> authServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Auth service is UNAVAILABLE, try again later");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profileServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Profile service is UNAVAILABLE, try again later");
    }

    @GetMapping("/rating")
    public ResponseEntity<String> ratingServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Rating service is UNAVAILABLE, try again later");
    }

    @GetMapping("/review")
    public ResponseEntity<String> reviewServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Review service is UNAVAILABLE, try again later");
    }
}
