package com.example.barseek_profile_mservice.kafka;

import com.example.barseek_profile_mservice.kafka.events.UserCreatedEvent;
import com.example.barseek_profile_mservice.model.entity.UserProfile;
import com.example.barseek_profile_mservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@KafkaListener(topics = "user.events",groupId = "profile-service-group")
public class KafkaConsumerService {

    private final UserProfileService userProfileService;

    @KafkaHandler
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        UserProfile profile = UserProfile.builder()
                .userId(event.getUserId())
                .email(event.getEmail())
                .build();
        userProfileService.createProfile(profile);
    }
}
