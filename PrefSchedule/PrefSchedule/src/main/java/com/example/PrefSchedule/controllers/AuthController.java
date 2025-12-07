package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.dto.LoginRequestDTO;
import com.example.PrefSchedule.dto.LoginResponseDTO;
import com.example.PrefSchedule.dto.RegisterRequestDTO;
import com.example.PrefSchedule.dto.RegisterResponseDTO;
import com.example.PrefSchedule.services.JwtService;
import com.example.PrefSchedule.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RegistrationService registrationService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwt, UserDetailsService uds, RegistrationService registrationService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwt;
        this.userDetailsService = uds;
        this.registrationService = registrationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        authenticationManager.authenticate(authentication);

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(registrationService.register(request));
    }
}


