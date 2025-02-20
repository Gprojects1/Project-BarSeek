package com.example.barseek_auth_mservice.service;


import com.example.barseek_auth_mservice.dto.AuthRequest;
import com.example.barseek_auth_mservice.dto.AuthResponse;
import com.example.barseek_auth_mservice.dto.RegisterRequest;
import com.example.barseek_auth_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_auth_mservice.exception.customExceptions.UserNotFoundException;
import com.example.barseek_auth_mservice.model.entity.User;
import com.example.barseek_auth_mservice.repository.UserRepository;
import com.example.barseek_auth_mservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) {

        if(userService.findByEmail(request.getEmail()).isPresent()) {
            throw new InvalidDataException("User with this email already exists!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .pass(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userService.addNew(user);

        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
