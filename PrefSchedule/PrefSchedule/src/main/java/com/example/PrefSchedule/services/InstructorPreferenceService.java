package com.example.PrefSchedule.services;

import com.example.PrefSchedule.dto.InstructorPreferenceRequestDTO;
import com.example.PrefSchedule.dto.InstructorPreferenceResponseDTO;
import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.InstructorPreference;
import com.example.PrefSchedule.repositories.CourseRepository;
import com.example.PrefSchedule.repositories.InstructorPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorPreferenceService {

    private final InstructorPreferenceRepository prefRepo;
    private final CourseRepository courseRepo;

    public InstructorPreferenceService(InstructorPreferenceRepository prefRepo,
                                       CourseRepository courseRepo) {
        this.prefRepo = prefRepo;
        this.courseRepo = courseRepo;
    }

    public List<InstructorPreferenceResponseDTO> getPreferences(Long optionalId) {
        Course optional = courseRepo.findById(optionalId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return prefRepo.findByOptionalCourse(optional)
                .stream()
                .map(p -> new InstructorPreferenceResponseDTO(
                        p.getId(),
                        optional.getId(),
                        p.getCompulsoryAbbr(),
                        p.getPercentage()
                ))
                .toList();
    }

    public InstructorPreferenceResponseDTO create(Long optionalId, InstructorPreferenceRequestDTO dto) {
        Course optional = courseRepo.findById(optionalId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (!optional.getType().equals("OPTIONAL"))
            throw new RuntimeException("Preferences only apply to OPTIONAL courses");

        InstructorPreference pref =
                new InstructorPreference(optional, dto.getCompulsoryAbbr(), dto.getPercentage());

        prefRepo.save(pref);

        return new InstructorPreferenceResponseDTO(
                pref.getId(),
                optional.getId(),
                pref.getCompulsoryAbbr(),
                pref.getPercentage()
        );
    }

    public void delete(Long id) {
        prefRepo.deleteById(id);
    }
}

