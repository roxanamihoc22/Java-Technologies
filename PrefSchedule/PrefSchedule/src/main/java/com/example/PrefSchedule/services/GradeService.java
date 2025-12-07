package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.Grade;
import com.example.PrefSchedule.entities.Student;
import com.example.PrefSchedule.repositories.CourseRepository;
import com.example.PrefSchedule.repositories.GradeRepository;
import com.example.PrefSchedule.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public GradeService(GradeRepository gradeRepo,
                        StudentRepository studentRepo,
                        CourseRepository courseRepo) {
        this.gradeRepo = gradeRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Grade> getAll() {
        return gradeRepo.findAll();
    }

    public List<Grade> getByStudentId(Long studentId) {
        return gradeRepo.findByStudentId(studentId);
    }

    public void importFromCsv(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line;
            boolean firstLine = true;
            List<Grade> batch = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 3) continue;

                String studentCode = parts[0].trim();
                String courseCode  = parts[1].trim();
                int gradeValue     = Integer.parseInt(parts[2].trim());

                Student student = studentRepo.findStudentByCode(studentCode);
                Course course = courseRepo.findCourseByCode(courseCode);

                if (student == null || course == null) continue;

                if (!course.getType().equalsIgnoreCase("COMPULSORY")) continue;

                batch.add(new Grade(student, course, gradeValue));
            }

            gradeRepo.saveAll(batch);

        } catch (Exception ex) {
            throw new RuntimeException("Failed to process CSV: " + ex.getMessage());
        }
    }
}

