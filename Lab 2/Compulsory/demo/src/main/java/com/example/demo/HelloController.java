package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // ← This class handles web requests
public class HelloController {
    @GetMapping("/api/hello")
    public String sayHello(String name) {
        return String.format("Hello, %s!", name);
    }
    @Value("${hello.message}")
    private String message;

    @GetMapping("/hello")
    public String sayHello() {
        return message;
    }
}

