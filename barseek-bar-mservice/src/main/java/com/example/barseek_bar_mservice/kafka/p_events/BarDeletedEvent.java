package com.example.barseek_bar_mservice.kafka.p_events;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarDeletedEvent {
    private Long barId;
}
