package com.example.PrefSchedule.services;

import com.example.StableMatch.dto.StableMatchRequestDTO;
import com.example.StableMatch.dto.StableMatchResponseDTO;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class StableMatchClient {

    private static final Logger log = LoggerFactory.getLogger(StableMatchClient.class);

    private final RestTemplate restTemplate;

    public StableMatchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @TimeLimiter(name = "stableMatchTimeout")
    @Retry(name = "stableMatchRetry",fallbackMethod = "fallbackResponse")
    public CompletableFuture<StableMatchResponseDTO> runMatching(StableMatchRequestDTO request) {

        System.out.println("Sending request to StableMatch…");

        return CompletableFuture.supplyAsync(() ->
                restTemplate.postForObject(
                        "http://localhost:8090/match/run",
                        request,
                        StableMatchResponseDTO.class
                )
        );
    }

    public String fallbackResponse(StableMatchResponseDTO res, Throwable t) {
        return "Request failed, please try later";
    }



}

