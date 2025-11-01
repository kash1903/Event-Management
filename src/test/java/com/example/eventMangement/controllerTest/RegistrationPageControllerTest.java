package com.example.eventMangement.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.ui.Model;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;

import com.example.eventMangement.controller.RegistrationPageController;
import com.example.eventMangement.model.Client;

public class RegistrationPageControllerTest {
    
      @Test
    void testShowRegisterPage() {
        RegistrationPageController controller = new RegistrationPageController();
        Model model = new ConcurrentModel();

        String viewName = controller.showRegisterPage(model);

        assertEquals("register", viewName);
        assertTrue(model.containsAttribute("client"));
        assertTrue(model.getAttribute("client") instanceof Client);
    }
}
