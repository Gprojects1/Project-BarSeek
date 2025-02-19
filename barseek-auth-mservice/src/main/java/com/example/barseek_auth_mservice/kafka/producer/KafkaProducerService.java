package com.example.barseek_auth_mservice.kafka.producer;

import com.example.barseek_auth_mservice.kafka.events.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("user.events", event);
    }
}
