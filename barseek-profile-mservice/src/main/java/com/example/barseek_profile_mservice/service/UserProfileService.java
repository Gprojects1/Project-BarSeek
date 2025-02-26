package com.example.barseek_profile_mservice.service;


import com.example.barseek_profile_mservice.dto.UpdateProfileRequest;
import com.example.barseek_profile_mservice.exception.customExceptions.ProfileNotFoundException;
import com.example.barseek_profile_mservice.model.entity.UserProfile;
import com.example.barseek_profile_mservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    @Autowired
    private final UserRepository userRepository;

    public UserProfile getUserProfile(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException("User does not exist, id : " + userId));
    }

    @Transactional
    public UserProfile updateUserProfile(Long userId, UpdateProfileRequest updateProfileRequest) {
        UserProfile exProfile = getUserProfile(userId);

        UserProfile newProfile = UserProfile.builder()
                .id(exProfile.getId())
                .userId(userId)
                .registrationDate(exProfile.getRegistrationDate())
                .avatar(exProfile.getAvatar())
                .sex(updateProfileRequest.getSex())
                .email(updateProfileRequest.getEmail())
                .phone(updateProfileRequest.getPhone())
                .birthDate(updateProfileRequest.getBirthDate())
                .firstName(updateProfileRequest.getFirstName())
                .secondName(updateProfileRequest.getSecondName())
                .build();

        deleteUserProfile(exProfile.getUserId());

        return userRepository.save(newProfile);
    }

    @Transactional
    public void deleteUserProfile(Long userId) {
        UserProfile exProfile = getUserProfile(userId);
        userRepository.deleteByUserId(userId);
    }



}
