package com.example.barseek_profile_mservice.controller;


import com.example.barseek_profile_mservice.model.UserProfile;
import com.example.barseek_profile_mservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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

    //@AuthenticationPrincipal |/
    @GetMapping("/me")
    public ResponseEntity<UserProfile> getMyProfile() {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateMyProfile() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyProfile() {
        return ResponseEntity.ok(null);
    }
}
