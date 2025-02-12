package com.example.barseek_bar_mservice.kafka.p_events;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BarCreatedEvent {
    private Long barId;
    private String name;
    private String address;
    private UUID ownerId;
}
