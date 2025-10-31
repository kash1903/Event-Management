package com.example.eventMangement.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eventMangement.model.AttendEvent;
import com.example.eventMangement.model.Event;
import com.example.eventMangement.model.RegisterEvent;
import com.example.eventMangement.repo.AttendEventRepo;
import com.example.eventMangement.repo.EventRepo;
import com.example.eventMangement.repo.RegisterEventRepo;

import java.util.*;

@Service
public class EventParticipationService {
    


       @Autowired
    private RegisterEventRepo registerRepo;

    @Autowired
    private AttendEventRepo attendRepo;

    @Autowired
    private EventRepo eventRepo;

    // 🔹 Register for Event
    public Map<String, Object> registerForEvent(Long eventId, String username, String email) {
        Map<String, Object> response = new HashMap<>();

        Optional<Event> eventOpt = eventRepo.findById(eventId);
        if (eventOpt.isEmpty()) {
            response.put("message", "❌ Event not found!");
            response.put("success", false);
            return response;
        }

        // Check if already registered
        Optional<RegisterEvent> existing = registerRepo.findByEmailAndEventId(email, eventId);
        if (existing.isPresent()) {
            response.put("message", "⚠️ Already registered for this event!");
            response.put("success", false);
            return response;
        }

        RegisterEvent register = new RegisterEvent();
        register.setUsername(username);
        register.setEmail(email);
        register.setEvent(eventOpt.get());
        registerRepo.save(register);

        response.put("message", "✅ Successfully registered for event!");
        response.put("success", true);
        return response;
    }

    // 🔹 Attend Event (only if registered)
    public Map<String, Object> attendEvent(Long eventId, String username, String email, String feedback) {
        Map<String, Object> response = new HashMap<>();

        Optional<Event> eventOpt = eventRepo.findById(eventId);
        if (eventOpt.isEmpty()) {
            response.put("message", "❌ Event not found!");
            response.put("success", false);
            return response;
        }

        // Check if registered first
        Optional<RegisterEvent> registered = registerRepo.findByEmailAndEventId(email, eventId);
        if (registered.isEmpty()) {
            response.put("message", "⚠️ You must register for this event first!");
            response.put("success", false);
            return response;
        }

        // Save attendance
        AttendEvent attend = new AttendEvent();
        attend.setUsername(username);
        attend.setEmail(email);
        attend.setFeedback(feedback);
        attend.setEvent(eventOpt.get());
        attendRepo.save(attend);

        response.put("message", "✅ Attendance marked successfully!");
        response.put("success", true);
        return response;
    }

    // 🔹 Get Event Stats for Admin
    public Map<String, Object> getEventStats(Long eventId) {
        Map<String, Object> response = new HashMap<>();

        Optional<Event> eventOpt = eventRepo.findById(eventId);
        if (eventOpt.isEmpty()) {
            response.put("message", "❌ Event not found!");
            return response;
        }

        int totalRegistered = registerRepo.findByEventId(eventId).size();
        int totalAttended = attendRepo.findByEventId(eventId).size();

        response.put("eventTitle", eventOpt.get().getEventTitle());
        response.put("totalRegistered", totalRegistered);
        response.put("totalAttended", totalAttended);
        response.put("success", true);
        return response;
}
}