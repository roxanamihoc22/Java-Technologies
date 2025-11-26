package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.Instructor;
import com.example.PrefSchedule.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAll() {
        return repo.findAll();
    }

    public List<Course> getByType(String type) {
        return repo.findByType(type);
    }

    public List<Course> getByPackYear(int year, int semester) {
        return repo.findCoursesByPackYear(year);
    }

    public List<Course> getInstructorsWithCourses() {
        return repo.findInstructorsWithCourses();
    }
}

