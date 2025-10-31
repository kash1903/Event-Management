package com.example.eventMangement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.example.eventMangement.model.Client;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationPageController {
    
    //  private static final String REGISTER_API_URL = "http://localhost:8080/api/clients/register";
    @Value("${app.base-url-clients}")
     private static  String REGISTER_API_URL ;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("client") Client client, Model model) {

        RestTemplate restTemplate = new RestTemplate();

        // Prepare request body
        Map<String, Object> request = new HashMap<>();
        request.put("username", client.getUsername());
        request.put("userEmail", client.getUserEmail());
        request.put("password", client.getPassword());
        request.put("role", client.getRole());

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(REGISTER_API_URL, entity, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && Boolean.TRUE.equals(responseBody.get("success"))) {
                model.addAttribute("message", responseBody.get("message"));
            } else {
                model.addAttribute("message", "❌ Registration failed!");
            }
        } catch (Exception e) {
            model.addAttribute("message", "⚠️ Error connecting to server: " + e.getMessage());
        }

        return "register";
}
}
