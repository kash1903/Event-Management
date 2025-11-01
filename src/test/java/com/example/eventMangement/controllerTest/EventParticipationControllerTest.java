package com.example.eventMangement.controllerTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
// import org.springframework.http.ResponseEntity;

import com.example.eventMangement.controller.EventParticipationController;
import com.example.eventMangement.service.EventParticipationService;

public class EventParticipationControllerTest {
     @InjectMocks
    private EventParticipationController controller;

    @Mock
    private EventParticipationService participationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterForEvent() {
        Map<String, Object> mockResponse = Map.of("status", "success");
        when(participationService.registerForEvent(1L, "John", "john@gmail.com"))
                .thenReturn(mockResponse);

        // ResponseEntity<Map<String, Object>> response =
        //         controller.registerForEvent(1L, "John", "john@gmail.com");

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("success", response.getBody().get("status"));
        verify(participationService, times(1))
                .registerForEvent(1L, "John", "john@gmail.com");
    }

    @Test
    void testAttendEvent() {
        Map<String, Object> mockResponse = Map.of("message", "Attendance marked");
        when(participationService.attendEvent(1L, "John", "john@gmail.com", "Great event!"))
                .thenReturn(mockResponse);

        // ResponseEntity<Map<String, Object>> response =
        //         controller.attendEvent(1L, "John", "john@gmail.com", "Great event!");

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("Attendance marked", response.getBody().get("message"));
        verify(participationService, times(1))
                .attendEvent(1L, "John", "john@gmail.com", "Great event!");
    }

    @Test
    void testGetEventStats() {
        Map<String, Object> mockStats = Map.of("registered", 10, "attended", 8);
        when(participationService.getEventStats(1L)).thenReturn(mockStats);

        // ResponseEntity<Map<String, Object>> response = controller.getEventStats(1L);

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals(10, response.getBody().get("registered"));
        // assertEquals(8, response.getBody().get("attended"));
        verify(participationService, times(1)).getEventStats(1L);
    }
}
