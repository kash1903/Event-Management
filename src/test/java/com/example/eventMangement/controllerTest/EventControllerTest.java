package com.example.eventMangement.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.*;

import com.example.eventMangement.controller.EventController;
import com.example.eventMangement.model.Event;
import com.example.eventMangement.service.EventService;

public class EventControllerTest {
    

    
    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
        event.setId(1L);
        event.setEventTitle("Tech Meetup");
        event.setVenue("Chennai");
    }

    @Test
    void testGetAllEvents() {
        when(eventService.getAllEvents()).thenReturn(Collections.singletonList(event));

        // ResponseEntity<List<Event>> response = eventController.getAllEvents();

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals(1, response.getBody().size());
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void testGetEventById_Found() {
        when(eventService.getEventById(1L)).thenReturn(Optional.of(event));

        // ResponseEntity<Event> response = eventController.getEventById(1L);

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("Tech Meetup", response.getBody().getEventTitle());
    }

    @Test
    void testGetEventById_NotFound() {
        when(eventService.getEventById(1L)).thenReturn(Optional.empty());

        // ResponseEntity<Event> response = eventController.getEventById(1L);

        // assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateEvent() {
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        // ResponseEntity<Event> response = eventController.createEvent(event);

        // assertEquals(200, response.getStatusCodeValue());
        // assertEquals("Tech Meetup", response.getBody().getEventTitle());
        verify(eventService, times(1)).createEvent(event);
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(eventService).deleteEvent(1L);

        ResponseEntity<String> response = eventController.deleteEvent(1L);

        // assertEquals(200, response.getStatusCodeValue());
        assertEquals("Event deleted successfully ✅", response.getBody());
        verify(eventService, times(1)).deleteEvent(1L);
    }

    @Test
    void testSearchEvents() {
        when(eventService.searchEvents("Tech")).thenReturn(Collections.singletonList(event));

        // ResponseEntity<List<Event>> response = eventController.searchEvents("Tech");

        // assertEquals(200, response.getStatusCodeValue());
        // assertFalse(response.getBody().isEmpty());
        verify(eventService, times(1)).searchEvents("Tech");
}
}
