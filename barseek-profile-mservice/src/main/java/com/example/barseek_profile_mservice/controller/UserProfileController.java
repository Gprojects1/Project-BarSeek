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
import org.springframework.web.multipart.MultipartFile;

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

    // | profile | //
    @GetMapping("/me")
    public ResponseEntity<UserProfile> getMyProfile(@RequestHeader(value = "X-User-Id") String userId,
                                                    @RequestHeader(value = "X-User-Roles") String roles
    ) {

        Long id = Long.parseLong(userId);
        UserProfile profile = userProfileService.getUserProfile(id);
        return ResponseEntity.ok(profile);

    }

    @PutMapping("/me")
    public ResponseEntity<String> updateMyProfile(@RequestHeader(value = "X-User-Id") String userId,
                                                  @RequestHeader(value = "X-User-Roles") String roles,
                                                  @RequestBody UpdateProfileRequest updateProfileRequest
    ) {
        Long id = Long.parseLong(userId);
        UserProfile newProfile = userProfileService.updateUserProfile(id,updateProfileRequest);
        return ResponseEntity.ok("Profile updated, id : " + newProfile.getId());
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyProfile(@RequestHeader(value = "X-User-Id") String userId,
                                                  @RequestHeader(value = "X-User-Roles") String roles) {

        Long id = Long.parseLong(userId);
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.ok("profile was deleted");

    }

    // | avatar | //

    @DeleteMapping("/me/avatar")
    public ResponseEntity<String> deleteMyAvatar(@RequestHeader(value = "X-User-Id") String userId,
                                                 @RequestHeader(value = "X-User-Roles") String roles
    ) {
        Long id = Long.parseLong(userId);
        userProfileService.deleteAvatar(id);
        return ResponseEntity.ok("avatar was successfully deleted");
    }

    @PostMapping("/me/avatar")
    public ResponseEntity<String> uploadMyAvatar(@RequestHeader(value = "X-User-Id") String userId,
                                                 @RequestHeader(value = "X-User-Roles") String roles,
                                                 @RequestParam ("file") MultipartFile file
    ) {
        Long id = Long.parseLong(userId);
        String filePath = userProfileService.uploadAvatar(id,file);
        return ResponseEntity.ok("avatar was uploaded, path : " + filePath);
    }

}
