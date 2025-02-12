package com.example.barseek_bar_mservice.kafka.consumer;

import com.example.barseek_bar_mservice.kafka.c_events.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "user.events", groupId = "bar-service-group")
    public void handleUserCreatedEvent(UserCreatedEvent event) {

        System.out.println("Received user created event: " + event);

    }
}
