package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.dto.*;
import com.example.PrefSchedule.exceptions.ResourceNotFoundException;
import com.example.PrefSchedule.services.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Get preferences for a single student",
            description = "Returns the students preferences."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Preferences retrieved successfully",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PreferenceResponseDTO.class))
                            ),
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = PreferenceResponseDTO.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
            )
    })

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

