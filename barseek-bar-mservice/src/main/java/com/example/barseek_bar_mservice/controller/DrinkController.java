package com.example.barseek_bar_mservice.controller;


import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.service.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drink-service-api/v1")
public class DrinkController {

    private final DrinkService drinkService;

    @PostMapping("/{barId}/drinks")
    public ResponseEntity<String> addNew(@PathVariable("barId") Long barId, @RequestBody Drink drink) {

            Drink newDrink = drinkService.addNewDrink(barId,drink);
            return new ResponseEntity<>("New drink created with name : " + newDrink.getName(),HttpStatus.CREATED);

    }

    @GetMapping("/{barId}/drinks/{drinkId}")
    public ResponseEntity<Drink> findById(@PathVariable("barId") Long barId, @PathVariable("drinkId") Long drinkId) {

            Drink drink = drinkService.findDrinkById(drinkId,barId);
            return ResponseEntity.ok(drink);
    }

    @DeleteMapping("/{barId}/drinks/{drinkId}")
    public ResponseEntity<String> deleteById(@PathVariable("barId") Long barId,@PathVariable("drinkId") Long drinkId) {

            drinkService.deleteDrinkById(barId,drinkId);
            return new ResponseEntity<>("Drink was deleted id :" + drinkId,HttpStatus.OK);
    }

    @GetMapping("/{barId}/drinks")
    public ResponseEntity<List<Drink>> findAllByBarId(@PathVariable("barId") Long id) {

            List<Drink> drinks = drinkService.findAllDrinksByBarId(id);
            return drinks.isEmpty() ?
                    new ResponseEntity("No drinks found", HttpStatus.NO_CONTENT) :
                    ResponseEntity.ok(drinks);

    }

    @PutMapping("/{barId}/drinks/{drinkId}")
    public ResponseEntity<String> updateById(
            @PathVariable("barId") Long barId,
            @PathVariable("drinkId") Long drinkId,
            @RequestBody Drink updatedDrink
    ) {

            Drink newDrink = drinkService.updateDrinkById(barId,drinkId,updatedDrink);
            return new ResponseEntity<>("Drink was saved and updated, name : " + newDrink.getName(),HttpStatus.OK);

    }

    // получить бар по коктейлю
    @GetMapping("/{drinkId}/bar")
    public ResponseEntity<Bar> findBarByDrinkId(@PathVariable("drinkId") Long id) {

            Bar bar = drinkService.findBarByDrinkId(id);
            return ResponseEntity.ok(bar);
    }



}
