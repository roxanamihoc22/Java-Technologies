package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.Instructor;
import com.example.PrefSchedule.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository repo;

    public InstructorService(InstructorRepository repo) {
        this.repo = repo;
    }

    public List<Instructor> getAll() {
        return repo.findAll();
    }

    public Instructor getByEmail(String email) {
        return repo.findByEmail(email);
    }

}

