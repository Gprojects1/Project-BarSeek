package com.example.barseek_profile_mservice.kafka;

import com.example.barseek_profile_mservice.kafka.events.ProfileDeletedEvent;
import com.example.barseek_profile_mservice.kafka.events.ProfileUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final String TOPIC_NAME = "profile.events";

    public void sendProfileDeletedEvent(ProfileDeletedEvent event) {
        kafkaTemplate.send(TOPIC_NAME,"deleted",event);
    }

    public void sendProfileUpdatedEvent(ProfileUpdatedEvent event) {
        kafkaTemplate.send(TOPIC_NAME,"updated",event);
    }

}
