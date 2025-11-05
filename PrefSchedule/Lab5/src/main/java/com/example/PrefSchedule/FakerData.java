package com.example.PrefSchedule;

import com.example.PrefSchedule.entities.*;
import com.example.PrefSchedule.repositories.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class FakerData implements CommandLineRunner {

    private final StudentRepository studentRepo;
    private final InstructorRepository instructorRepo;
    private final PackRepository packRepo;
    private final CourseRepository courseRepo;

    public FakerData(StudentRepository studentRepo,
                                InstructorRepository instructorRepo,
                                PackRepository packRepo,
                                CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.instructorRepo = instructorRepo;
        this.packRepo = packRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Faker faker = new Faker(new Locale("en"));
        Random random = new Random();

        System.out.println("=== Using Java Faker to populate database ===");

        List<Instructor> instructors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Instructor instructor = new Instructor(
                    null,
                    faker.name().fullName(),
                    faker.internet().emailAddress()
            );
            instructors.add(instructorRepo.save(instructor));
        }

        List<Pack> packs = new ArrayList<>();
        for (int y = 1; y <= 3; y++) {
            for (int sem = 1; sem <= 2; sem++) {
                Pack pack = new Pack(null, y, sem, "Pack " + y + "-" + sem);
                packs.add(packRepo.save(pack));
            }
        }

        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Instructor instructor = instructors.get(random.nextInt(instructors.size()));
            Pack pack = random.nextBoolean() ? packs.get(random.nextInt(packs.size())) : null;

            String type = (pack == null) ? "COMPULSORY" : "OPTIONAL";
            String abbr = faker.educator().course().substring(0, 3).toUpperCase();

            Course course = new Course(
                    null,
                    type,
                    "C" + faker.number().digits(4),
                    abbr,
                    faker.educator().course(),
                    instructor,
                    pack,
                    random.nextInt(4) + 1,
                    faker.lorem().sentence(6)
            );
            courses.add(courseRepo.save(course));
        }

        for (int i = 0; i < 8; i++) {
            Student s = new Student(
                    null,
                    "S" + faker.number().digits(4),
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    random.nextInt(3) + 1
            );
            studentRepo.save(s);
        }

        Instructor randomInstructor = instructors.get(0);
        Course newCourse = new Course(
                null,
                "COMPULSORY",
                "CS999",
                "ALG",
                "Advanced Algorithms",
                randomInstructor,
                null,
                3,
                "Complexity theory and algorithms."
        );
        courseRepo.save(newCourse);
        System.out.println("Created: " + newCourse);
    }
}

