package com.example.eventMangement.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.eventMangement.model.Event;
import com.example.eventMangement.repo.EventRepo;
import com.example.eventMangement.service.EventService;
import java.util.*;

public class EventServiceTest {
    
     @Mock
    private EventRepo eventRepo;

    @InjectMocks
    private EventService eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
        event.setId(1L);
        event.setEventTitle("Tech Talk");
        event.setVenue("Chennai");
        event.setSpeaker("John Doe");
        event.setDescription("AI and Future");
        // event.setDate("2025-11-05");
    }

    @Test
    void testGetAllEvents() {
        when(eventRepo.findAll()).thenReturn(Arrays.asList(event));

        List<Event> result = eventService.getAllEvents();

        assertEquals(1, result.size());
        assertEquals("Tech Talk", result.get(0).getEventTitle());
        verify(eventRepo, times(1)).findAll();
    }

    @Test
    void testGetEventById() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));

        Optional<Event> found = eventService.getEventById(1L);

        assertTrue(found.isPresent());
        assertEquals("Tech Talk", found.get().getEventTitle());
    }

    @Test
    void testCreateEvent() {
        when(eventRepo.save(event)).thenReturn(event);

        Event created = eventService.createEvent(event);

        assertNotNull(created);
        assertEquals("Chennai", created.getVenue());
        verify(eventRepo, times(1)).save(event);
    }

    @Test
    void testUpdateEvent() {
        Event updatedEvent = new Event();
        updatedEvent.setEventTitle("Updated Talk");
        updatedEvent.setVenue("Bangalore");
        updatedEvent.setSpeaker("Jane Doe");
        updatedEvent.setDescription("Updated Desc");
        // updatedEvent.setDate("2025-12-10");

        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(eventRepo.save(any(Event.class))).thenReturn(updatedEvent);

        Event result = eventService.updateEvent(1L, updatedEvent);

        assertEquals("Updated Talk", result.getEventTitle());
        verify(eventRepo, times(1)).findById(1L);
        verify(eventRepo, times(1)).save(any(Event.class));
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(eventRepo).deleteById(1L);

        eventService.deleteEvent(1L);

        verify(eventRepo, times(1)).deleteById(1L);
    }

    @Test
    void testSearchEvents() {
        when(eventRepo.findByEventTitleContainingIgnoreCaseOrVenueContainingIgnoreCase("tech", "tech"))
                .thenReturn(List.of(event));

        List<Event> result = eventService.searchEvents("tech");

        assertEquals(1, result.size());
        assertEquals("Tech Talk", result.get(0).getEventTitle());
    }

    @Test
    void testGetEventsByAdmin() {
        when(eventRepo.findByAdminId(10L)).thenReturn(List.of(event));

        List<Event> events = eventService.getEventsByAdmin(10L);

        assertEquals(1, events.size());
        assertEquals("Tech Talk", events.get(0).getEventTitle());
        verify(eventRepo, times(1)).findByAdminId(10L);
    }
}
