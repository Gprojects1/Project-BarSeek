package com.example.barseek_auth_mservice.service;


import com.example.barseek_auth_mservice.dto.AuthRequest;
import com.example.barseek_auth_mservice.dto.AuthResponse;
import com.example.barseek_auth_mservice.dto.RegisterRequest;
import com.example.barseek_auth_mservice.exception.customExceptions.AuthException;
import com.example.barseek_auth_mservice.exception.customExceptions.InvalidDataException;
import com.example.barseek_auth_mservice.exception.customExceptions.UserNotFoundException;
import com.example.barseek_auth_mservice.model.entity.User;
import com.example.barseek_auth_mservice.model.type.Role;
import com.example.barseek_auth_mservice.repository.UserRepository;
import com.example.barseek_auth_mservice.security.JwtUtil;
import jakarta.transaction.Transactional;
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


    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if(request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new InvalidDataException("Cant register without email");
        }

        if(request.getRole() == null) request.setRole(Role.USER);

        if(request.getPassword() == null || request.getPassword().length() < 8) {
            throw new InvalidDataException("Bad password");
        }

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

    @Transactional
    public AuthResponse login(AuthRequest request) {


        User user = userService.findByEmail(request.getEmail())
                        .orElseThrow(() -> new AuthException("Wrong email!"));

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
