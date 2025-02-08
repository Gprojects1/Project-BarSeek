package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.model.Drink;
import com.example.barseek_bar_mservice.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("drink-service-api/v1")
public class DrinkController {

    private final DrinkService drinkService;

    @PostMapping("/{barId}/drinks")
    public ResponseEntity<String> addNew(@PathVariable("barId") Long barId,@RequestBody Drink drink) {
        try {
            Optional<Drink> newDrink = drinkService.addNewDrink(barId,drink);
            return newDrink.map(value -> new ResponseEntity<>("New drink created with name : " + value.getName(), HttpStatus.CREATED)).
                    orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
