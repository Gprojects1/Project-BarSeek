package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.model.Bar;
import com.example.barseek_bar_mservice.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarService {

    private final BarRepository barRepository;


    public Bar addNewBar(Bar bar) {
        return barRepository.save(bar);
    }

    public Bar findBarById(Long id) {
        // кастом
        return barRepository.findById(id).orElse(null);
    }

    public List<Bar> findBarByName(String name) {
        return barRepository.findAllByName(name).orElse(new ArrayList<>());
    }

    public void deleteBarById(Long id) {
        // переделать , тут проверять если бар с айдишником . если нет -> кастом екс.
        barRepository.deleteById(id);
    }

    public void updateBarById(Bar updatedBar) {
        // см метод выше.
        deleteBarById(updatedBar.getId());
        addNewBar(updatedBar);
    }

}
