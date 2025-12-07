package com.example.PrefSchedule.services;

import com.example.PrefSchedule.dto.*;
import com.example.PrefSchedule.entities.*;
import com.example.PrefSchedule.exceptions.ResourceNotFoundException;
import com.example.PrefSchedule.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreferenceService {

    private final StudentPreferenceRepository prefRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final PackRepository packRepo;

    public PreferenceService(StudentPreferenceRepository prefRepo, StudentRepository studentRepo,
                             CourseRepository courseRepo, PackRepository packRepo) {
        this.prefRepo = prefRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.packRepo = packRepo;
    }

    public List<PreferenceResponseDTO> getAll() {
        return prefRepo.findAll().stream()
                .map(p -> new PreferenceResponseDTO(
                        p.getId(),
                        p.getStudent().getName(),
                        p.getCourse().getName(),
                        p.getPack().getName(),
                        p.getRank()))
                .collect(Collectors.toList());
    }

    public List<PreferenceResponseDTO> getByStudent(Long studentId) {
        Student s = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student with ID " + studentId + " not found"));
        return prefRepo.findByStudent(s).stream()
                .map(p -> new PreferenceResponseDTO(
                        p.getId(),
                        s.getName(),
                        p.getCourse().getName(),
                        p.getPack().getName(),
                        p.getRank()))
                .collect(Collectors.toList());
    }

    public PreferenceResponseDTO create(PreferenceRequestDTO dto) {
        Optional<Student> s = studentRepo.findById(dto.getStudentId());
        Optional<Course> c = courseRepo.findById(dto.getCourseId());
        Optional<Pack> p = packRepo.findById(dto.getPackId());

        StudentPreference pref = new StudentPreference(null, s.orElse(null), c.orElse(null), p.orElse(null), dto.getRank());
        StudentPreference saved = prefRepo.save(pref);

        return new PreferenceResponseDTO(
                saved.getId(),
                s.get().getName(),
                c.get().getName(),
                p.get().getName(),
                saved.getRank());
    }
}

