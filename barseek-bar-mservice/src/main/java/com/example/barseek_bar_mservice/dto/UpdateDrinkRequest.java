package com.example.barseek_bar_mservice.dto;

import com.example.barseek_bar_mservice.model.types.DrinkSaleStatus;
import lombok.Data;

@Data
public class UpdateDrinkRequest {
    private String name;
    private String info;
    private DrinkSaleStatus drinkSaleStatus;
    private Integer degree;
}
