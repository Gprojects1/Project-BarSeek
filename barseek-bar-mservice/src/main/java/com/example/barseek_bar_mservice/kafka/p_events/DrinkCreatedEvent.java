package com.example.barseek_bar_mservice.kafka.p_events;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrinkCreatedEvent {
    private Long drinkId;
    private String name;
    private String barId;
}
