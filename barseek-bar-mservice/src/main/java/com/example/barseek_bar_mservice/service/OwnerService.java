package com.example.barseek_bar_mservice.service;

import com.example.barseek_bar_mservice.exception.customExceptions.OwnerNotFoundException;
import com.example.barseek_bar_mservice.exception.customExceptions.UnauthorizedAccessException;
import com.example.barseek_bar_mservice.model.entity.Owner;
import com.example.barseek_bar_mservice.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Owner addNewOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Owner findOwnerById(Long ownerId) {
        return ownerRepository.findByUserId(ownerId).
                orElseThrow(() -> new UnauthorizedAccessException("No bar owner with id : " + ownerId));
    }
}
