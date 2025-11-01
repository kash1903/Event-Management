package com.example.eventMangement.serviceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.eventMangement.repo.ClientRepo;
import com.example.eventMangement.service.ClientService;
import java.util.*;
import com.example.eventMangement.model.Client;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServiceTest {
    

      @Mock
    private ClientRepo clientRepo;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client();
        client.setUsername("John");
        client.setUserEmail("john@example.com");
        client.setPassword("1234");
    }

    @Test
    void testRegisterClient_Success() {
        when(clientRepo.findAll()).thenReturn(Collections.emptyList());
        when(clientRepo.save(any(Client.class))).thenReturn(client);

        Map<String, Object> response = clientService.registerClient(client);

        assertThat(response.get("success")).isEqualTo(true);
        assertThat(response.get("message")).isEqualTo("✅ Successfully Registered!");
    }

    @Test
    void testRegisterClient_EmailAlreadyExists() {
        when(clientRepo.findAll()).thenReturn(List.of(client));

        Map<String, Object> response = clientService.registerClient(client);

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("❌ Email already registered!");
    }

    @Test
    void testLoginClient_Success() {
        when(clientRepo.findAll()).thenReturn(List.of(client));

        Map<String, Object> response = clientService.loginClient("john@example.com", "1234");

        assertThat(response.get("success")).isEqualTo(true);
        assertThat(response.get("message")).isEqualTo("✅ Login Successful!");
    }

    @Test
    void testLoginClient_InvalidPassword() {
        when(clientRepo.findAll()).thenReturn(List.of(client));

        Map<String, Object> response = clientService.loginClient("john@example.com", "wrong");

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("❌ Invalid Password!");
    }

    @Test
    void testLoginClient_NotFound() {
        when(clientRepo.findAll()).thenReturn(Collections.emptyList());

        Map<String, Object> response = clientService.loginClient("unknown@example.com", "1234");

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("❌ Client not found!");
    }
}
