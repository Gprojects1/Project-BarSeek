package com.example.barseek_bar_mservice.kafka.producer;


import com.example.barseek_bar_mservice.kafka.p_events.DrinkCreatedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.DrinkDeletedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.DrinkUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaDrinkProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final String topicName = "drinks.events";

    public void sendDrinkCreatedEvent(DrinkCreatedEvent event) {
        kafkaTemplate.send(topicName,"created",event);
    }

    public void sendDrinkDeletedEvent(DrinkDeletedEvent event) {
        kafkaTemplate.send(topicName,"deleted",event);
    }

    public void sendDrinkUpdatedEvent(DrinkUpdatedEvent event) {
        kafkaTemplate.send(topicName,"updated",event);
    }

}
