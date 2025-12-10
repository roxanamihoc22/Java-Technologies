package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.dto.InstructorPreferenceRequestDTO;
import com.example.PrefSchedule.dto.InstructorPreferenceResponseDTO;
import com.example.PrefSchedule.services.InstructorPreferenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor/preferences")
public class InstructorPreferenceController {

    private final InstructorPreferenceService service;

    public InstructorPreferenceController(InstructorPreferenceService service) {
        this.service = service;
    }

    @GetMapping("/optional/{courseId}")
    public List<InstructorPreferenceResponseDTO> get(@PathVariable Long courseId) {
        return service.getPreferences(courseId);
    }

    @PostMapping("/optional/{courseId}")
    public InstructorPreferenceResponseDTO create(
            @PathVariable Long courseId,
            @RequestBody InstructorPreferenceRequestDTO dto
    ) {
        return service.create(courseId, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

