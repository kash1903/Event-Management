package com.example.eventMangement.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.eventMangement.controller.LandingController;

public class LandingControllerTest {
     @Test
    void testShowLandingPage() {
        LandingController controller = new LandingController();

        String viewName = controller.showLandingPage();

        assertEquals("index", viewName, "Landing page should return 'index' view");
    }
}
