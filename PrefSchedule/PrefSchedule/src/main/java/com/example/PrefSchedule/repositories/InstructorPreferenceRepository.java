package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.InstructorPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorPreferenceRepository
        extends JpaRepository<InstructorPreference, Long> {

    List<InstructorPreference> findByOptionalCourse(Course optionalCourse);
}

