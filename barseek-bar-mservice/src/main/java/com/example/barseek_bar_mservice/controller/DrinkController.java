package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("drink-service-api/v1")
public class DrinkController {

    private final DrinkService drinkService;

}
