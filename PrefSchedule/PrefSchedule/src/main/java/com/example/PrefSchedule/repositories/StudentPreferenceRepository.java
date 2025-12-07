package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentPreferenceRepository extends JpaRepository<StudentPreference, Long> {

    List<StudentPreference> findByStudent(Student student);

    List<StudentPreference> findByStudentAndPack(Student student, Pack pack);
}

