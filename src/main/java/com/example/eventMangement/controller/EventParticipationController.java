package com.example.eventMangement.controller;


import com.example.eventMangement.service.EventParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "*")
public class EventParticipationController {
    


      @Autowired
    private EventParticipationService participationService;

    // ✅ Register for event
    @PostMapping("/{eventId}/register")
    public ResponseEntity<Map<String, Object>> registerForEvent(
            @PathVariable Long eventId,
            @RequestParam String username,
            @RequestParam String email) {
        return ResponseEntity.ok(participationService.registerForEvent(eventId, username, email));
    }

    // ✅ Attend event
    @PostMapping("/{eventId}/attend")
    public ResponseEntity<Map<String, Object>> attendEvent(
            @PathVariable Long eventId,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String feedback) {
        return ResponseEntity.ok(participationService.attendEvent(eventId, username, email, feedback));
    }

    // ✅ Event stats for admin
    @GetMapping("/{eventId}/stats")
    public ResponseEntity<Map<String, Object>> getEventStats(@PathVariable Long eventId) {
        return ResponseEntity.ok(participationService.getEventStats(eventId));
}
}