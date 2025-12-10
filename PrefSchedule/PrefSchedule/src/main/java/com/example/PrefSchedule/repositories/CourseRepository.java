package com.example.PrefSchedule.repositories;

import java.util.List;
import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByType(String type);

    Course findCourseByCode(String code);

    @Query("SELECT c FROM Course c WHERE c.pack.year = :year")
    List<Course> findCoursesByPackYear(@Param("year") int year);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.instructor")
    List<Course> findInstructorsWithCourses();

    List<Course> findByPack(Pack pack);
}


