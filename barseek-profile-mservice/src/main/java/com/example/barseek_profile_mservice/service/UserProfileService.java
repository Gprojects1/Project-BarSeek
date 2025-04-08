package com.example.barseek_profile_mservice.service;


import com.example.barseek_profile_mservice.dto.UpdateProfileRequest;
import com.example.barseek_profile_mservice.exception.customExceptions.AvatarNotFoundException;
import com.example.barseek_profile_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_profile_mservice.exception.customExceptions.ProfileNotFoundException;
import com.example.barseek_profile_mservice.kafka.KafkaProducerService;
import com.example.barseek_profile_mservice.kafka.events.EmailChangedEvent;
import com.example.barseek_profile_mservice.kafka.events.ProfileDeletedEvent;
import com.example.barseek_profile_mservice.kafka.events.ProfileUpdatedEvent;
import com.example.barseek_profile_mservice.model.entity.Avatar;
import com.example.barseek_profile_mservice.model.entity.UserProfile;
import com.example.barseek_profile_mservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    @Autowired
    private final UserRepository userRepository;
    private final AvatarService avatarService;
    private final KafkaProducerService kafkaProducerService;


    @Transactional
    public void createProfile(UserProfile profile) {
        profile.setRegistrationDate(LocalDateTime.now());
        userRepository.save(profile);
    }

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
        userRepository.save(newProfile);

        if(!(exProfile.getEmail().equals(newProfile.getEmail()))) {
            EmailChangedEvent event = EmailChangedEvent.builder()
                    .userId(userId)
                    .email(newProfile.getEmail())
                    .build();
            kafkaProducerService.sendEmailChangedEvent(event);
        }

        ProfileUpdatedEvent event = ProfileUpdatedEvent.builder()
                .email(newProfile.getEmail())
                .birthDate(newProfile.getBirthDate())
                .firstName(newProfile.getFirstName())
                .phone(newProfile.getPhone())
                .userId(newProfile.getUserId())
                .secondName(newProfile.getSecondName())
                .build();
        kafkaProducerService.sendProfileUpdatedEvent(event);

        return newProfile;
    }

    @Transactional
    public void deleteUserProfile(Long userId) {
        UserProfile exProfile = getUserProfile(userId);

        ProfileDeletedEvent event = ProfileDeletedEvent.builder()
                .userId(userId)
                .email(exProfile.getEmail())
                .build();
        kafkaProducerService.sendProfileDeletedEvent(event);

        userRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteAvatar(Long userId) {
        UserProfile profile = getUserProfile(userId);

        if(profile.getAvatar() == null) {
            throw new AvatarNotFoundException("You have no avatar");
        }

        avatarService.deleteAvatarFile(Paths.get(profile.getAvatar().getFilePath()));

        Long avatarId = profile.getAvatar().getId();
        avatarService.deleteAvatarById(avatarId);
        profile.setAvatar(null);
        userRepository.save(profile);
    }

    @Transactional
    public String uploadAvatar(Long userId, MultipartFile file) {

        if(file.isEmpty()) {
            throw new InvalidDataException("file is empty");
        }

        UserProfile profile = getUserProfile(userId);
        if(profile.getAvatar() != null) deleteAvatar(userId);

        Path filePath = avatarService.downloadAvatarFile(file);

        Avatar newAvatar = Avatar.builder()
                .userProfile(profile)
                .filePath(filePath.toString())
                .build();
        avatarService.addNew(newAvatar);

        profile.setAvatar(newAvatar);

        try {
            userRepository.save(profile);
        } catch (Exception ex) {
            avatarService.deleteAvatarFile(filePath);
        }

        return filePath.toString();
    }



}
