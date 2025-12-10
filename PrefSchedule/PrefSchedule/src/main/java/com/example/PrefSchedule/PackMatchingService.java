package com.example.PrefSchedule;

import com.example.PrefSchedule.entities.Course;
import com.example.PrefSchedule.entities.Pack;
import com.example.PrefSchedule.entities.Student;
import com.example.PrefSchedule.entities.StudentPreference;
import com.example.PrefSchedule.repositories.CourseRepository;
import com.example.PrefSchedule.repositories.PackRepository;
import com.example.PrefSchedule.repositories.StudentPreferenceRepository;
import com.example.PrefSchedule.services.StableMatchClient;
import com.example.StableMatch.dto.StableMatchRequestDTO;
import com.example.StableMatch.dto.StableMatchResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PackMatchingService {

    private final PackRepository packRepo;
    private final CourseRepository courseRepo;
    private final StudentPreferenceRepository prefRepo;
    private final StableMatchClient stableMatchClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public PackMatchingService(
            PackRepository packRepo,
            CourseRepository courseRepo,
            StudentPreferenceRepository prefRepo,
            StableMatchClient stableMatchClient) {

        this.packRepo = packRepo;
        this.courseRepo = courseRepo;
        this.prefRepo = prefRepo;
        this.stableMatchClient = stableMatchClient;
    }

    public StableMatchResponseDTO runPackMatching(Long packId) {

        Pack pack = packRepo.findById(packId)
                .orElseThrow(() -> new RuntimeException("Pack not found"));

        // 1. Cursurile din pack
        List<Course> courses = courseRepo.findByPack(pack);
        if (courses.isEmpty()) {
            throw new RuntimeException("No courses found for pack " + packId);
        }

        // 2. Preferințele studentilor pentru acest pack
        List<StudentPreference> prefsForPack = prefRepo.findByPack(pack);
        if (prefsForPack.isEmpty()) {
            throw new RuntimeException("No student preferences found for pack " + packId);
        }

        // 3. Studenții distinct din preferințe
        List<Student> students = prefsForPack.stream()
                .map(StudentPreference::getStudent)
                .distinct()
                .toList();

        // ---------- Request DTO ----------
        StableMatchRequestDTO req = new StableMatchRequestDTO();

        req.setStudents(
                students.stream().map(Student::getCode).toList()
        );

        req.setCourses(
                courses.stream().map(Course::getCode).toList()
        );

        // 4. Student preferences: doar pentru acest pack, ordonate după rank
        Map<String, List<String>> studentPrefs = new HashMap<>();

        for (Student s : students) {
            List<String> ordered = prefsForPack.stream()
                    .filter(sp -> sp.getStudent().getId().equals(s.getId()))
                    .sorted(Comparator.comparingInt(StudentPreference::getRank))
                    .map(sp -> sp.getCourse().getCode())
                    .toList();

            studentPrefs.put(s.getCode(), ordered);
        }

        req.setStudentPreferences(studentPrefs);

        // 5. Course preferences RANDOM
        Map<String, List<String>> coursePrefs = new HashMap<>();
        List<String> studentCodes = req.getStudents();

        for (Course c : courses) {
            List<String> shuffled = new ArrayList<>(studentCodes);
            Collections.shuffle(shuffled);
            coursePrefs.put(c.getCode(), shuffled);
        }

        req.setCoursePreferences(coursePrefs);

        // (opțional) log JSON-ul trimis
        try {
            var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            System.out.println("=== StableMatchRequest JSON ===");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(req));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ---------- Call StableMatch ----------
        return stableMatchClient.runMatching(req);
    }

}


