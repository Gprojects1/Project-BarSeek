package com.example.barseek_bar_mservice.kafka.p_events;


import com.example.barseek_bar_mservice.model.types.DrinkSaleStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrinkUpdatedEvent {
    private Long drinkId;
    private Long barId;
    private String newName;
    private String newDescription;
    private DrinkSaleStatus newStatus;
}
