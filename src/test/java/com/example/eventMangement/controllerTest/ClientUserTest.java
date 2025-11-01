package com.example.eventMangement.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.eventMangement.controller.ClientUser;
import com.example.eventMangement.model.Client;

public class ClientUserTest {
    
    private ClientUser clientUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientUser = new ClientUser();
    }

    @Test
    void testShowClientPage() {
        // Mock Model
        Model model = mock(Model.class);

        // Call method
        String viewName = clientUser.showClientPage(model);

        // Verify
        verify(model, times(1)).addAttribute(eq("client"), any(Client.class));
        assertEquals("client", viewName); // ensure correct template name
}
}
