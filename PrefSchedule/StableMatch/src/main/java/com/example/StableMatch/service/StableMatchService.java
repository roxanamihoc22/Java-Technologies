package com.example.StableMatch.service;

import com.example.StableMatch.dto.AssignmentDTO;
import com.example.StableMatch.dto.StableMatchRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StableMatchService {

    private static final Logger log = LoggerFactory.getLogger(StableMatchService.class);

    private List<AssignmentDTO> lastAssignments = new ArrayList<>();

    public List<AssignmentDTO> getAssignments() {
        return lastAssignments;
    }

    public List<AssignmentDTO> run(StableMatchRequestDTO req) {

        log.info("=== StableMatch REQUEST RECEIVED ===");
        log.info("Students: {}", req.getStudents());
        log.info("Courses: {}", req.getCourses());
        log.info("Student Preferences: {}", req.getStudentPreferences());
        log.info("Course Preferences: {}", req.getCoursePreferences());
        log.info("====================================");

        List<String> students = req.getStudents();
        List<String> courses = req.getCourses();

        Map<String, List<String>> studPref = req.getStudentPreferences();
        Map<String, List<String>> coursePref = req.getCoursePreferences();

        // Ranking courses’ preferences
        Map<String, Map<String, Integer>> rank = new HashMap<>();
        for (String c : courses) {
            rank.put(c, new HashMap<>());
            List<String> prefs = coursePref.get(c);
            for (int i = 0; i < prefs.size(); i++) {
                rank.get(c).put(prefs.get(i), i);
            }

            log.info("Course {} preference ranking: {}", c, rank.get(c));
        }

        Map<String, String> assigned = new HashMap<>();
        Map<String, Integer> next = new HashMap<>();
        Queue<String> free = new LinkedList<>(students);

        students.forEach(s -> next.put(s, 0));

        log.info("=== START MATCHING ===");

        while (!free.isEmpty()) {
            String s = free.poll();
            List<String> prefs = studPref.get(s);
            int idx = next.get(s);

            String c = prefs.get(idx);
            next.put(s, idx + 1);

            log.info("Student {} applies to course {} (rank {})", s, c, idx);

            if (!assigned.containsValue(c)) {
                assigned.put(s, c);
                log.info(" → Course {} was free. Assigned {}.", c, s);
            } else {
                String current = assigned.entrySet().stream()
                        .filter(e -> e.getValue().equals(c))
                        .findFirst()
                        .get().getKey();

                log.info(" → Course {} already assigned to {}", c, current);

                if (rank.get(c).get(s) < rank.get(c).get(current)) {
                    log.info(" → {} is preferred over {} by course {}", s, current, c);
                    assigned.put(s, c);
                    free.add(current); // current goes back to queue
                    log.info(" → {} becomes free again", current);
                } else {
                    log.info(" → {} rejected by course {}", s, c);
                    free.add(s); // student tries next course next round
                }
            }
        }

        lastAssignments = assigned.entrySet().stream()
                .map(e -> new AssignmentDTO(e.getKey(), e.getValue()))
                .toList();

        log.info("=== FINAL ASSIGNMENTS ===");
        lastAssignments.forEach(a ->
                log.info("Student {} → Course {}", a.getStudentCode(), a.getCourseCode())
        );
        log.info("===========================");

        return lastAssignments;
    }

    public List<AssignmentDTO> getForStudent(String student) {
        return lastAssignments.stream()
                .filter(a -> a.getStudentCode().equals(student))
                .toList();
    }

    public List<AssignmentDTO> getForCourse(String course) {
        return lastAssignments.stream()
                .filter(a -> a.getCourseCode().equals(course))
                .toList();
    }
}


