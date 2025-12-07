package com.example.PrefSchedule.repositories;

import com.example.PrefSchedule.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    Student findStudentByCode(String code);

    @Query("SELECT s FROM Student s WHERE s.year = :year")
    List<Student> findStudentsByYear(@Param("year") int year);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.year = s.year + 1 WHERE s.year < 6")
    int promoteAllStudents();
}
