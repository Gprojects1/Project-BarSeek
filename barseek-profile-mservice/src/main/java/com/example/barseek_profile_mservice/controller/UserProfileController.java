package com.example.barseek_profile_mservice.controller;


import com.example.barseek_profile_mservice.model.UserProfile;
import com.example.barseek_profile_mservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile-service-api/v1/profiles")
public class UserProfileController {

    @Autowired
    private final UserProfileService userProfileService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfile> findById(@PathVariable(name = "profileId") Long id) {
        return ResponseEntity.ok(null);
    }
}
