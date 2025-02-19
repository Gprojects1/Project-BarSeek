package com.example.barseek_auth_mservice.service;


import com.example.barseek_auth_mservice.model.entity.User;
import com.example.barseek_auth_mservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User addNew(User user) {
        return userRepository.save(user);
    }
}
