package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.Pack;
import com.example.PrefSchedule.repositories.PackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    private final PackRepository repo;

    public PackService(PackRepository repo) {
        this.repo = repo;
    }

    public List<Pack> getAll() {
        return repo.findAll();
    }

    public List<Pack> getByYear(int year) {
        return repo.findByYear(year);
    }
}

