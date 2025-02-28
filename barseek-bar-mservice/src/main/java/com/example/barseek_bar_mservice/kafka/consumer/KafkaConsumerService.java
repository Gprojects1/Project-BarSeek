package com.example.barseek_bar_mservice.kafka.consumer;

import com.example.barseek_bar_mservice.kafka.c_events.UserCreatedEvent;
import com.example.barseek_bar_mservice.model.entity.Owner;
import com.example.barseek_bar_mservice.service.BarService;
import com.example.barseek_bar_mservice.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@KafkaListener(topics = "user.events", groupId = "bar-service-group")
public class KafkaConsumerService {

    private final OwnerService ownerService;

    @KafkaHandler
    public void handleUserCreatedEvent(UserCreatedEvent event) {

        System.out.println("Received user created event: " + event);

        if("bar".equals(event.getRole())) {
            Owner owner = Owner.builder()
                    .userId(event.getUserId())
                    .email(event.getEmail())
                    .role(event.getRole())
                    .build();
            ownerService.addNewOwner(owner);
        }

    }
}
