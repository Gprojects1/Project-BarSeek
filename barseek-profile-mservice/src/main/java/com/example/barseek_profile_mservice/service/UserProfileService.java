package com.example.barseek_profile_mservice.service;


import com.example.barseek_profile_mservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    @Autowired
    private final UserRepository userRepository;
}
