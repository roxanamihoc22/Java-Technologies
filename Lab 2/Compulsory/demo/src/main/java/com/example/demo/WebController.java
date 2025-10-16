package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ← This class is a web component
public class WebController {
    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "Hello from Spring Boot!");
        return "welcome"; // The name of a template
    }
} // Controllers are managed by the Spring IoC container.
