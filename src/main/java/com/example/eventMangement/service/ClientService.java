package com.example.eventMangement.service;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventMangement.repo.ClientRepo;

import com.example.eventMangement.model.Client;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepo clientRepo;

    // 🔹 Register new Client/Admin
    public Map<String, Object> registerClient(Client client) {
        Map<String, Object> response = new HashMap<>();

        // Check if email already exists
        Optional<Client> existingClient = clientRepo.findAll()
                .stream()
                .filter(c -> c.getUserEmail().equalsIgnoreCase(client.getUserEmail()))
                .findFirst();

        if (existingClient.isPresent()) {
            response.put("message", "❌ Email already registered!");
            response.put("success", false);
            return response;
        }

        Client savedClient = clientRepo.save(client);
        savedClient.setPassword(null); // Hide password in response

        response.put("message", "✅ Successfully Registered!");
        response.put("success", true);
        response.put("client", savedClient);
        return response;
    }

    // 🔹 Login (by username OR email)
    public Map<String, Object> loginClient(String identifier, String password) {
        Map<String, Object> response = new HashMap<>();

        Optional<Client> clientOpt = clientRepo.findAll()
                .stream()
                .filter(c -> c.getUserEmail().equalsIgnoreCase(identifier)
                        || c.getUsername().equalsIgnoreCase(identifier))
                .findFirst();

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (client.getPassword().equals(password)) {
                client.setPassword(null);
                response.put("message", "✅ Login Successful!");
                response.put("success", true);
                response.put("client", client);
            } else {
                response.put("message", "❌ Invalid Password!");
                response.put("success", false);
            }
        } else {
            response.put("message", "❌ Client not found!");
            response.put("success", false);
        }

        return response;

    
}
}
