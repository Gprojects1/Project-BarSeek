package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.model.Bar;
import com.example.barseek_bar_mservice.model.Drink;
import com.example.barseek_bar_mservice.repository.BarRepository;
import com.example.barseek_bar_mservice.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final BarRepository barRepository;

    public Optional<Drink> addNewDrink(Long barId, Drink drink) {
        Optional<Bar> bar = barRepository.findById(barId);
        if(bar.isEmpty()) return Optional.empty();
        drink.setBar(bar.get());
        return Optional.of(drinkRepository.save(drink));
    }
}
