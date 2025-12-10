package com.example.StableMatch.controller;

import com.example.StableMatch.dto.AssignmentDTO;
import com.example.StableMatch.dto.StableMatchRequestDTO;
import com.example.StableMatch.dto.StableMatchResponseDTO;
import com.example.StableMatch.service.StableMatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stable")
public class StableMatchController {

    private final StableMatchService service;

    public StableMatchController(StableMatchService service) {
        this.service = service;
    }

    @PostMapping("/match")
    public StableMatchResponseDTO match(@RequestBody StableMatchRequestDTO dto) {
        return new StableMatchResponseDTO(service.run(dto));
    }

    @GetMapping("/assignments")
    public List<AssignmentDTO> all() {
        return service.getAssignments();
    }

    @GetMapping("/assignments/student/{id}")
    public List<AssignmentDTO> forStudent(@PathVariable String id) {
        return service.getForStudent(id);
    }

    @GetMapping("/assignments/course/{id}")
    public List<AssignmentDTO> forCourse(@PathVariable String id) {
        return service.getForCourse(id);
    }
}


