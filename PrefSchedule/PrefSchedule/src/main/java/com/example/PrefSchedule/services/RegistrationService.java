package com.example.PrefSchedule.services;

import com.example.PrefSchedule.dto.RegisterRequestDTO;
import com.example.PrefSchedule.dto.RegisterResponseDTO;
import com.example.PrefSchedule.entities.User;
import com.example.PrefSchedule.entities.Role;
import com.example.PrefSchedule.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterResponseDTO register(RegisterRequestDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole()));

        User saved = userRepository.save(user);

        return new RegisterResponseDTO(saved.getId(), saved.getEmail(), saved.getRole().name());
    }
}

