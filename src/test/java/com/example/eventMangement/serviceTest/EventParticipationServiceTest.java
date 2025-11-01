package com.example.eventMangement.serviceTest;

import static org.mockito.Mockito.when;


import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.eventMangement.model.AttendEvent;
import com.example.eventMangement.model.Event;
import com.example.eventMangement.model.RegisterEvent;
import com.example.eventMangement.repo.AttendEventRepo;
import com.example.eventMangement.repo.EventRepo;
import com.example.eventMangement.repo.RegisterEventRepo;
import com.example.eventMangement.service.EventParticipationService;
import static org.assertj.core.api.Assertions.assertThat;

public class EventParticipationServiceTest {
      @Mock
    private RegisterEventRepo registerRepo;

    @Mock
    private AttendEventRepo attendRepo;

    @Mock
    private EventRepo eventRepo;

    @InjectMocks
    private EventParticipationService participationService;

    private Event event;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        event = new Event();
        event.setId(1L);
        event.setEventTitle("Tech Talk");
    }

    // ✅ Test Register Event Success
    @Test
    void testRegisterForEvent_Success() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(registerRepo.findByEmailAndEventId("john@example.com", 1L))
                .thenReturn(Optional.empty());

        Map<String, Object> response = participationService.registerForEvent(1L, "John", "john@example.com");

        assertThat(response.get("success")).isEqualTo(true);
        assertThat(response.get("message")).isEqualTo("✅ Successfully registered for event!");
    }

    // ❌ Event Not Found
    @Test
    void testRegisterForEvent_EventNotFound() {
        when(eventRepo.findById(1L)).thenReturn(Optional.empty());

        Map<String, Object> response = participationService.registerForEvent(1L, "John", "john@example.com");

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("❌ Event not found!");
    }

    // ⚠️ Already Registered
    @Test
    void testRegisterForEvent_AlreadyRegistered() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(registerRepo.findByEmailAndEventId("john@example.com", 1L))
                .thenReturn(Optional.of(new RegisterEvent()));

        Map<String, Object> response = participationService.registerForEvent(1L, "John", "john@example.com");

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("⚠️ Already registered for this event!");
    }

    // ✅ Attend Event Success
    @Test
    void testAttendEvent_Success() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(registerRepo.findByEmailAndEventId("john@example.com", 1L))
                .thenReturn(Optional.of(new RegisterEvent()));

        Map<String, Object> response =
                participationService.attendEvent(1L, "John", "john@example.com", "Great event!");

        assertThat(response.get("success")).isEqualTo(true);
        assertThat(response.get("message")).isEqualTo("✅ Attendance marked successfully!");
    }

    // ⚠️ Not Registered Yet
    @Test
    void testAttendEvent_NotRegistered() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(registerRepo.findByEmailAndEventId("john@example.com", 1L))
                .thenReturn(Optional.empty());

        Map<String, Object> response =
                participationService.attendEvent(1L, "John", "john@example.com", "Great event!");

        assertThat(response.get("success")).isEqualTo(false);
        assertThat(response.get("message")).isEqualTo("⚠️ You must register for this event first!");
    }

    // ✅ Get Stats
    @Test
    void testGetEventStats_Success() {
        when(eventRepo.findById(1L)).thenReturn(Optional.of(event));
        when(registerRepo.findByEventId(1L)).thenReturn(List.of(new RegisterEvent(), new RegisterEvent()));
        when(attendRepo.findByEventId(1L)).thenReturn(List.of(new AttendEvent()));

        Map<String, Object> response = participationService.getEventStats(1L);

        assertThat(response.get("success")).isEqualTo(true);
        assertThat(response.get("eventTitle")).isEqualTo("Tech Talk");
        assertThat(response.get("totalRegistered")).isEqualTo(2);
        assertThat(response.get("totalAttended")).isEqualTo(1);
    }

    // ❌ Stats Event Not Found
    @Test
    void testGetEventStats_NotFound() {
        when(eventRepo.findById(1L)).thenReturn(Optional.empty());

        Map<String, Object> response = participationService.getEventStats(1L);

        assertThat(response.get("message")).isEqualTo("❌ Event not found!");
    }
}
