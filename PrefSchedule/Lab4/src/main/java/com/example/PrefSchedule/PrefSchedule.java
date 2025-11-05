package com.example.PrefSchedule;

import com.example.PrefSchedule.services.CourseService;
import com.example.PrefSchedule.services.InstructorService;
import com.example.PrefSchedule.services.PackService;
import com.example.PrefSchedule.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PrefSchedule implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(PrefSchedule.class, args);
	}

    @Autowired
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final PackService packService;

    public PrefSchedule(InstructorService instructorService, StudentService studentService, CourseService courseService, PackService packService) {
        this.instructorService = instructorService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.packService = packService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Students queries:");
        studentService.getAllStudents().forEach(System.out::println);
        System.out.println("All Students in second year:");
        studentService.getByYear(2).forEach(System.out::println);
        System.out.println("Update all students with year + 1.");
        int updated = studentService.updateYear();
        System.out.println(updated + " Students year updated");

        System.out.println("Course queries:");
        courseService.getByPackYear(1,1).forEach(System.out::println);

    }

}
