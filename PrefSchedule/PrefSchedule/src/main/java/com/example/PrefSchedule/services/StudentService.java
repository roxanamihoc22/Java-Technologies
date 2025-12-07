package com.example.PrefSchedule.services;


import com.example.PrefSchedule.entities.Student;
import com.example.PrefSchedule.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Student> getByYear(int year) {
        return repo.findStudentsByYear(year);
    }

    public int updateYear() {
        return repo.promoteAllStudents();
    }

    //from controller
    public Student createStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Long id, Student newData) {
        Optional<Student> opt = repo.findById(id);
        if (opt.isEmpty()) return null;
        Student existing = opt.get();
        existing.setName(newData.getName());
        existing.setEmail(newData.getEmail());
        existing.setCode(newData.getCode());
        existing.setYear(newData.getYear());
        return repo.save(existing);
    }

    public boolean deleteStudent(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }



}

