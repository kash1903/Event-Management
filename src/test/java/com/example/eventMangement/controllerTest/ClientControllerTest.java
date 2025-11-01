package com.example.eventMangement.controllerTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;
import java.util.*;

import com.example.eventMangement.controller.ClientController;
import com.example.eventMangement.model.Client;
import com.example.eventMangement.service.ClientService;

public class ClientControllerTest {
     @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterClient() {
        // Mock client input
        Client client = new Client();
        client.setUsername("John");
        // client.setEmail("john@example.com");
        client.setPassword("password123");

        // Mock service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("message", "Registration successful");

        when(clientService.registerClient(any(Client.class))).thenReturn(mockResponse);

        // Call controller method
        // ResponseEntity<Map<String, Object>> response = clientController.registerClient(client);

        // Verify and assert
        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("Registration successful", response.getBody().get("message"));
        verify(clientService, times(1)).registerClient(any(Client.class));
    }

    @Test
    void testLoginClient() {
        // Mock service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("message", "Login successful");

        when(clientService.loginClient("john@example.com", "password123")).thenReturn(mockResponse);

        // Call controller method
        // ResponseEntity<Map<String, Object>> response = clientController.loginClient("john@example.com", "password123");

        // Verify and assert
        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("Login successful", response.getBody().get("message"));
        verify(clientService, times(1)).loginClient("john@example.com", "password123");
}
}
