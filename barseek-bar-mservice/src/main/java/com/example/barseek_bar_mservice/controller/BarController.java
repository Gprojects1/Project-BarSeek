package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("bar-service-api/bars/v1")
public class BarController {

    private final BarService barService;

    public BarController(BarService barService) {
        this.barService = barService;

    }
}
