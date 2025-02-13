package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.exception.customExceptions.BarNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_bar_mservice.kafka.p_events.BarCreatedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.BarDeletedEvent;
import com.example.barseek_bar_mservice.kafka.p_events.BarUpdatedEvent;
import com.example.barseek_bar_mservice.kafka.producer.KafkaBarProducerService;
import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.model.entity.Drink;
import com.example.barseek_bar_mservice.model.entity.Owner;
import com.example.barseek_bar_mservice.repository.BarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BarService {

    private final BarRepository barRepository;

    private final KafkaBarProducerService kafkaBarProducerService;
    private final OwnerService ownerService;


    @Transactional
    public Bar addNewBar(Bar bar, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);

        if(bar.getName() == null || bar.getName().isEmpty()) {
            throw new InvalidDataException("Bar can not have an empty name!");
        }

        Bar savedBar = barRepository.save(bar);

        BarCreatedEvent event = BarCreatedEvent.builder()
                .barId(savedBar.getId())
                .address(savedBar.getAddress())
                .ownerId(savedBar.getOwnerId())
                .name(savedBar.getName())
                .build();
        kafkaBarProducerService.sendBarCreatedEvent(event);

        return barRepository.save(bar);
    }

    public Bar findBarById(Long id) {
        return barRepository.findById(id).
                orElseThrow(() -> new BarNotFoundException("bar does not exists, id : " + id));
    }

    public List<Bar> findBarByName(String name) {
        if(name == null || name.isEmpty()) {
            throw new InvalidDataException("The name is empty!");
        }
        return barRepository.findAllByName(name).orElse(new ArrayList<>());
    }

    @Transactional
    public void deleteBarById(Long id, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);

        Bar exBar = findBarById(id);

        BarDeletedEvent event = BarDeletedEvent.builder()
                .barId(exBar.getId())
                .build();
        kafkaBarProducerService.sendBarDeletedEvent(event);

        barRepository.deleteById(id);
    }

    @Transactional
    public Bar updateBarById(Long id, Bar updatedBar, Long ownerId) {

        Owner owner = ownerService.findOwnerById(ownerId);
        Bar exBar = findBarById(id);
        updatedBar.setId(exBar.getId());
        barRepository.deleteById(id);
        Bar newBar = addNewBar(updatedBar);

        BarUpdatedEvent event = BarUpdatedEvent.builder()
                .barId(newBar.getId())
                .newAddress(newBar.getAddress())
                .newName(newBar.getName())
                .build();
        kafkaBarProducerService.sendBarUpdatedEvent(event);

        return addNewBar(updatedBar);
    }

    @Transactional
    public List<Drink> findAllDrinksById(Long id) {
        Bar bar = findBarById(id);
        return bar.getDrinks();
    }
}
