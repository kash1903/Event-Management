package com.example.eventMangement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.eventMangement.service.ClientService;
import com.example.eventMangement.model.Client;
import java.util.Map;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")

public class ClientController {
    
  @Autowired
    private ClientService clientService;

    // ✅ Register endpoint
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.registerClient(client));
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginClient(@RequestParam String identifier, @RequestParam String password) {
        return ResponseEntity.ok(clientService.loginClient(identifier, password));
}
}
