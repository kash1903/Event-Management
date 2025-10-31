package com.example.eventMangement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {
    
     @GetMapping("/")
    public String showLandingPage() {
        return "index"; // Renders index.html
    }

}
