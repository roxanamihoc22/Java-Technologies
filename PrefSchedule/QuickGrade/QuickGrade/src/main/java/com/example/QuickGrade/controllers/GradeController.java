package com.example.QuickGrade.controllers;

import com.example.QuickGrade.dto.GradeMessageDTO;
import com.example.QuickGrade.services.GradeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService publisher;

    public GradeController(GradeService publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public String sendGrade(@RequestBody GradeMessageDTO dto) {
        publisher.publish(dto);
        return "Event published!";
    }
}
