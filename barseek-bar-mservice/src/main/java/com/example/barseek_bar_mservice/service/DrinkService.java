package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.exception.customExceptions.DrinkNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.repository.BarRepository;
import com.example.barseek_bar_mservice.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    private final BarService barService;

    public Drink addNewDrink(Long barId, Drink drink) {

        if(drink.getName() == null || drink.getName().isEmpty()) {
            throw new InvalidDataException("Drink can not have an empty name!");
        }

        Bar bar = barService.findBarById(barId);
        drink.setBar(bar);
        return drinkRepository.save(drink);
    }

    public Drink findDrinkById(Long drinkId, Long barId) {

        Bar bar = barService.findBarById(barId);

        return drinkRepository.findByIdAndBarId(drinkId,barId).
                orElseThrow(() -> new DrinkNotFoundException("Drink does not exists in chosen bar!"));
    }


    public void deleteDrinkById(Long barId, Long drinkId) {

        Drink drink = findDrinkById(drinkId,barId);
        drinkRepository.deleteByBarIdAndId(barId,drinkId);

    }

    public List<Drink> findAllDrinksByBarId(Long id) {

        Bar bar = barService.findBarById(id);
        return drinkRepository.findAllByBarId(id).orElse(new ArrayList<>());

    }


    public Drink updateDrinkById(Long barId, Long drinkId, Drink updatedDrink) {

        Drink exDrink = findDrinkById(drinkId,barId);
        updatedDrink.setId(exDrink.getId());
        drinkRepository.deleteByBarIdAndId(barId,drinkId);
        return addNewDrink(barId,updatedDrink);

    }

    public Bar findBarByDrinkId(Long id) {

        Drink drink = drinkRepository.findById(id).
                orElseThrow(() -> new DrinkNotFoundException("No drink with given id!"));

        return drink.getBar();

    }
}

// получить бар по коктелю => в дринк сервис
// получить список коктелей по бару => в бар сервис
