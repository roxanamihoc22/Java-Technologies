package com.example.PrefSchedule.controllers;

import com.example.PrefSchedule.PackMatchingService;
import com.example.StableMatch.dto.StableMatchResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/packs")
public class PackMatchingController {

    private final PackMatchingService matchingService;

    public PackMatchingController(PackMatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping("/{packId}/match")
    public StableMatchResponseDTO runMatching(@PathVariable Long packId) {

        return matchingService.runPackMatching(packId);
    }
}
