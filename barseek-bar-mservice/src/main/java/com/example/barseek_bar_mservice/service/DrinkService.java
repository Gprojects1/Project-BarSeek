package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.exception.customExceptions.DrinkNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_bar_mservice.kafka.p_events.DrinkCreatedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.DrinkDeletedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.DrinkUpdatedEvent;
import com.example.barseek_bar_mservice.kafka.producer.KafkaDrinkProducerService;
import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.model.entity.Owner;
import com.example.barseek_bar_mservice.repository.BarRepository;
import com.example.barseek_bar_mservice.repository.DrinkRepository;
import jakarta.transaction.Transactional;
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
    private final KafkaDrinkProducerService kafkaDrinkProducerService;
    private final OwnerService ownerService;

    @Transactional
    public Drink addNewDrink(Long barId, Drink drink, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);

        if(drink.getName() == null || drink.getName().isEmpty()) {
            throw new InvalidDataException("Drink can not have an empty name!");
        }

        Bar bar = barService.findBarById(barId);
        drink.setBar(bar);
        Drink newDrink = drinkRepository.save(drink);

        DrinkCreatedEvent event = DrinkCreatedEvent.builder()
                .drinkId(newDrink.getId())
                .name(newDrink.getName())
                .barId(newDrink.getBar().getId())
                .build();
        kafkaDrinkProducerService.sendDrinkCreatedEvent(event);

        return newDrink;
    }

    public Drink findDrinkById(Long drinkId, Long barId) {

        Bar bar = barService.findBarById(barId);

        return drinkRepository.findByIdAndBarId(drinkId,barId).
                orElseThrow(() -> new DrinkNotFoundException("Drink does not exists in chosen bar!"));
    }


    @Transactional
    public void deleteDrinkById(Long barId, Long drinkId, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);

        Drink drink = findDrinkById(drinkId,barId);

        DrinkDeletedEvent event = DrinkDeletedEvent.builder()
                .drinkId(drink.getId())
                .barId(drink.getBar().getId())
                .build();
        kafkaDrinkProducerService.sendDrinkDeletedEvent(event);

        drinkRepository.deleteByBarIdAndId(barId,drinkId);

    }

    @Transactional
    public List<Drink> findAllDrinksByBarId(Long id) {

        Bar bar = barService.findBarById(id);
        return drinkRepository.findAllByBarId(id).orElse(new ArrayList<>());

    }

    @Transactional
    public Drink updateDrinkById(Long barId, Long drinkId, Drink updatedDrink, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);

        Drink exDrink = findDrinkById(drinkId,barId);
        updatedDrink.setId(exDrink.getId());
        drinkRepository.deleteByBarIdAndId(barId,drinkId);
        Drink newDrink = addNewDrink(barId,updatedDrink);

        DrinkUpdatedEvent event = DrinkUpdatedEvent.builder()
                .drinkId(newDrink.getId())
                .barId(newDrink.getBar().getId())
                .newDescription(newDrink.getInfo())
                .newName(newDrink.getName())
                .newStatus(newDrink.getStatus())
                .build();
        kafkaDrinkProducerService.sendDrinkUpdatedEvent(event);

        return newDrink;
    }

    public Bar findBarByDrinkId(Long id) {

        Drink drink = drinkRepository.findById(id).
                orElseThrow(() -> new DrinkNotFoundException("No drink with given id!"));

        return drink.getBar();

    }
}

// получить список коктелей по бару => в бар сервис
