package com.example.barseek_auth_mservice.kafka.consumer;


import com.example.barseek_auth_mservice.kafka.events.ProfileDeletedEvent;
import com.example.barseek_auth_mservice.kafka.events.ProfileUpdatedEvent;
import com.example.barseek_auth_mservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@KafkaListener(topics = "profile.events", groupId = "auth-service-group")
public class KafkaConsumerService {

    private final UserService userService;

    @KafkaHandler
    public void handleProfileDeletedEvent(ProfileDeletedEvent event) {
        userService.deleteUserByIdAndEmail(event.getUserId(), event.getEmail());
    }

    @KafkaHandler
    public void handleProfileUpdatedEvent(ProfileUpdatedEvent event) {
        userService.changeEmailById(event.getUserId(), event.getEmail());
    }

    @KafkaHandler(isDefault = true)
    public void handleOtherEvents(Object event) {
        log.warn("ignoring..", event);
    }
}
