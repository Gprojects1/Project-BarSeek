package com.example.barseek_bar_mservice.service;


import com.example.barseek_bar_mservice.repository.BarRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class BarService {

    private final BarRepository barRepository;

    public BarService(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

}
