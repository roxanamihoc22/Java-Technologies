package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.dto.*;
import com.example.PrefSchedule.exceptions.ResourceNotFoundException;
import com.example.PrefSchedule.services.PreferenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    private final PreferenceService service;

    public PreferenceController(PreferenceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PreferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity<List<PreferenceResponseDTO>> getByStudent(@PathVariable Long studentId) {
            List<PreferenceResponseDTO> preferences = service.getByStudent(studentId);
            return ResponseEntity.ok(preferences);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(ResourceNotFoundException ex) {
        return Map.of(
                "error", ex.getMessage(),
                "status", "404"
        );
    }

    @PostMapping (produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<PreferenceResponseDTO> addPref(@Valid @RequestBody PreferenceRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

}

