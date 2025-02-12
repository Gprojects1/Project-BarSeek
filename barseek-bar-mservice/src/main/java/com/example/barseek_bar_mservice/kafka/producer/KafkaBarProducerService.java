package com.example.barseek_bar_mservice.kafka.producer;


import com.example.barseek_bar_mservice.kafka.p_events.BarCreatedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.BarDeletedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.BarUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaBarProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final String topicName = "bar.events";

    public void sendBarCreatedEvent(BarCreatedEvent event) {

        kafkaTemplate.send(topicName,"created",event);
    }

    public void sendBarDeletedEvent(BarDeletedEvent event) {

        kafkaTemplate.send(topicName,"deleted",event);
    }

    public void sendBarUpdatedEvent(BarUpdatedEvent event) {

        kafkaTemplate.send(topicName,"updated",event);
    }

}
