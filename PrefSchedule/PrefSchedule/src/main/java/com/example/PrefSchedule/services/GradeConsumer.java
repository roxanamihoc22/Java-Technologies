package com.example.PrefSchedule.services;

import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.Grade;
import com.example.PrefSchedule.entities.Student;
import com.example.PrefSchedule.repositories.CourseRepository;
import com.example.PrefSchedule.repositories.GradeRepository;
import com.example.PrefSchedule.repositories.StudentRepository;
import com.example.QuickGrade.dto.GradeMessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GradeConsumer {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final GradeRepository gradeRepo;

    public GradeConsumer(StudentRepository studentRepo,
                         CourseRepository courseRepo,
                         GradeRepository gradeRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.gradeRepo = gradeRepo;
    }

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "prefschedule-group")
    public void consumeGrade(GradeMessageDTO event) {

        System.out.println("Received grade event: " + event.getStudentCode() +
                ", " + event.getCourseCode() + ", grade = " + event.getGrade());

        Student student = studentRepo.findStudentByCode(event.getStudentCode());
        Course course = courseRepo.findCourseByCode(event.getCourseCode());

        if (course.getType().equalsIgnoreCase("COMPULSORY")) {
            System.out.println("Compulsory course: " + course.getCode());
            return;
        }

        Grade grade = new Grade(student, course, event.getGrade());
        gradeRepo.save(grade);

        System.out.println("Saved grade for " + student.getId() + " in course " + course.getId());
    }
}


