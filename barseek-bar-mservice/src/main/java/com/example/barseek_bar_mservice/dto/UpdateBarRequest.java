package com.example.barseek_bar_mservice.dto;

import com.example.barseek_bar_mservice.model.types.BarType;
import lombok.Data;

@Data
public class UpdateBarRequest {
    private String name;
    private String address;
    private BarType barType;
}
