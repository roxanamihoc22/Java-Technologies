package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.entities.Grade;
import com.example.PrefSchedule.services.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAll());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getByStudentId(studentId));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {

        gradeService.importFromCsv(file);
        return ResponseEntity.ok("CSV uploaded and processed successfully.");
    }
}

