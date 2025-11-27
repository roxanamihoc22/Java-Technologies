package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.User;
import com.example.PrefSchedule.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println("email:" + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + email));
        System.out.println("email:" + user.getPassword());
        return org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}

