package com.example.barseek_auth_mservice.service;


import com.example.barseek_auth_mservice.exception.customExceptions.UserNotFoundException;
import com.example.barseek_auth_mservice.kafka.events.UserCreatedEvent;
import com.example.barseek_auth_mservice.kafka.producer.KafkaProducerService;
import com.example.barseek_auth_mservice.model.entity.User;
import com.example.barseek_auth_mservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final KafkaProducerService kafkaProducerService;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User addNew(User user) {
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .id(user.getId())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
        kafkaProducerService.sendUserCreatedEvent(userCreatedEvent);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User does not exist..."));
    }
}
