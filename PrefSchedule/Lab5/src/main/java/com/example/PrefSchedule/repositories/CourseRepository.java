package com.example.PrefSchedule.repositories;

import java.util.List;
import com.example.PrefSchedule.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByType(String type);

    @Query("SELECT c FROM Course c WHERE c.pack.year = :year")
    List<Course> findCoursesByPackYear(@Param("year") int year);

}


