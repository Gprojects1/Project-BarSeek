package com.example.barseek_profile_mservice.controller;


import com.example.barseek_profile_mservice.dto.UpdateProfileRequest;
import com.example.barseek_profile_mservice.model.entity.UserProfile;
import com.example.barseek_profile_mservice.security.UserPrincipal;
import com.example.barseek_profile_mservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<UserProfile> getMyProfile(@AuthenticationPrincipal UserPrincipal principal) {

        Long userId = Long.parseLong(principal.getUsername());
        UserProfile profile = userProfileService.getUserProfile(userId);
        return ResponseEntity.ok(profile);

    }

    @PutMapping("/me")
    public ResponseEntity<String> updateMyProfile(@AuthenticationPrincipal UserPrincipal principal,
                                                  @RequestBody UpdateProfileRequest updateProfileRequest) {
        Long userId = Long.parseLong(principal.getUsername());
        UserProfile newProfile = userProfileService.updateUserProfile(userId,updateProfileRequest);
        return ResponseEntity.ok("Profile updated, id : " + newProfile.getId());
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyProfile(@AuthenticationPrincipal UserPrincipal principal) {

        Long userId = Long.parseLong(principal.getUsername());
        userProfileService.deleteUserProfile(userId);
        return ResponseEntity.ok("profile was deleted");

    }
}
