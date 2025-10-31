package com.example.eventMangement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.eventMangement.model.Client;
import com.example.eventMangement.repo.ClientRepo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {


       @Autowired
    private ClientRepo clientRepo;

    // ✅ Web Login Page (for Thymeleaf)
    @GetMapping
    public String showLoginPage() {
        return "login"; 
    }

    // ✅ Handles login via web form
    // @PostMapping("/loginUser")
    // public String loginUser(@RequestParam String username,
    //                         @RequestParam String password,
    //                         Model model,
    //                         HttpSession session) {

    //     Client client = clientRepo.findByUsernameAndPassword(username, password);

    //     if (client != null) {
    //         session.setAttribute("client", client);
    //         model.addAttribute("client", client);

    //         if ("ADMIN".equalsIgnoreCase(client.getRole())) {
    //             return "admin"; // admin.html
    //         } else {
    //             return "client"; // client.html
    //         }
    //     }

    //     model.addAttribute("error", "Invalid username or password!");
    //     return "login";
    // }

    // new login logic start
    @PostMapping("/loginUser")
public String loginUser(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {

    // Try login using username first
    Client client = clientRepo.findByUsernameAndPassword(username, password);

    // If not found, try email
    if (client == null) {
        client = clientRepo.findByUserEmailAndPassword(username, password);
    }

    if (client != null) {
        session.setAttribute("client", client);
        model.addAttribute("client", client);

        if ("ADMIN".equalsIgnoreCase(client.getRole())) {
            return "admin";
        } else {
            return "client";
        }
    }

    model.addAttribute("error", "Invalid username/email or password!");
    return "login";
}
 // new login logic end

    // ✅ NEW: REST API Login (for Postman or frontend)
    // @PostMapping("/api")
    // @ResponseBody
    // public ResponseEntity<?> apiLogin(@RequestParam String username,
    //                                   @RequestParam String password) {

    //     Client client = clientRepo.findByUsernameAndPassword(username, password);

    //     if (client != null) {
    //         Map<String, Object> response = new HashMap<>();
    //         response.put("success", true);
    //         response.put("username", client.getUsername());
    //         response.put("id", client.getId()); // ✅ Include admin/client ID
    //         response.put("role", client.getRole());
    //         response.put("message", "Login successful ✅");

    //         return ResponseEntity.ok(response);
    //     }

    //     Map<String, Object> error = new HashMap<>();
    //     error.put("success", false);
    //     error.put("message", "Invalid username or password ❌");

    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    // }

    // new api logic start
    @PostMapping("/api")
@ResponseBody
public ResponseEntity<?> apiLogin(@RequestParam String username,
                                  @RequestParam String password) {

    Client client = clientRepo.findByUsernameAndPassword(username, password);

    if (client == null) {
        client = clientRepo.findByUserEmailAndPassword(username, password);
    }

    if (client != null) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("username", client.getUsername());
        response.put("id", client.getId());
        response.put("role", client.getRole());
        response.put("message", "Login successful ✅");

        return ResponseEntity.ok(response);
    }

    Map<String, Object> error = new HashMap<>();
    error.put("success", false);
    error.put("message", "Invalid username/email or password ❌");

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
}

    // new api logic end


    }
