package com.example.eventMangement.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import org.springframework.ui.Model;

import com.example.eventMangement.controller.AdminController;
import com.example.eventMangement.service.AdminService;

public class AdminControllerTest  {
    

      @Mock
    private AdminService adminService;

    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test showAdminPage()
    @Test
    void testShowAdminPage() {
        List<Map<String, Object>> events = new ArrayList<>();
        when(adminService.getAllEvents()).thenReturn(events);

        String view = adminController.showAdminPage(model);

        verify(adminService).getAllEvents();
        verify(model).addAttribute("events", events);
        assertEquals("admin", view);
    }

    // ✅ Test searchEvents()
    @Test
    void testSearchEvents() {
        String keyword = "Tech";
        List<Map<String, Object>> events = new ArrayList<>();
        when(adminService.searchEvents(keyword)).thenReturn(events);

        String view = adminController.searchEvents(keyword, model);

        verify(adminService).searchEvents(keyword);
        verify(model).addAttribute("events", events);
        assertEquals("admin", view);
    }

    // ✅ Test createEvent()
    @Test
    void testCreateEvent() {
        Map<String, Object> eventData = new HashMap<>();

        String result = adminController.createEvent(eventData);

        verify(adminService).createEvent(eventData);
        assertEquals("redirect:/admin", result);
    }

    // ✅ Test deleteEvent()
    @Test
    void testDeleteEvent() {
        Long id = 1L;

        String result = adminController.deleteEvent(id);

        verify(adminService).deleteEvent(id);
        assertEquals("redirect:/admin", result);
    }

    // ✅ Test getEventStats()
    @Test
    void testGetEventStats() {
        Long eventId = 10L;
        Map<String, Object> stats = new HashMap<>();
        stats.put("registered", 50);
        when(adminService.getEventStats(eventId)).thenReturn(stats);

        Map<String, Object> result = adminController.getEventStats(eventId);

        verify(adminService).getEventStats(eventId);
        assertEquals(stats, result);
}
}
