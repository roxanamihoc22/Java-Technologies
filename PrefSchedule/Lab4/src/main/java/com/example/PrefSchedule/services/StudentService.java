package com.example.PrefSchedule.services;


import com.example.PrefSchedule.entities.Student;
import com.example.PrefSchedule.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public List<Student> getByYear(int year) {
        return repo.findStudentsByYear(year);
    }

    public int updateYear() {
        return repo.promoteAllStudents();
    }
}

