package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.exception.customExceptions.BarNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_bar_mservice.model.entity.Bar;
import com.example.barseek_bar_mservice.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarService {

    private final BarRepository barRepository;


    public Bar addNewBar(Bar bar) {
        if(bar.getName() == null || bar.getName().isEmpty()) {
            throw new InvalidDataException("Bar can not have an empty name!");
        }
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

    public void deleteBarById(Long id) {
        Bar exBar = findBarById(id);
        barRepository.deleteById(id);
    }

    public void updateBarById(Long id, Bar updatedBar) {
        Bar exBar = findBarById(id);
        updatedBar.setId(exBar.getId());
        deleteBarById(id);
        addNewBar(updatedBar);
    }

}
