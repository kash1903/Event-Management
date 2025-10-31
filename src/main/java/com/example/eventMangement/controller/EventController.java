package com.example.eventMangement.controller;


import com.example.eventMangement.model.Event;
import com.example.eventMangement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*") // allows frontend apps (like React) to access it

public class EventController {
    

      @Autowired
    private EventService eventService;


    @GetMapping("/byAdmin/{adminId}")
    public ResponseEntity<List<Event>> getEventsByAdmin(@PathVariable Long adminId) {
    List<Event> events = eventService.getEventsByAdmin(adminId);
    return ResponseEntity.ok(events);
}


    // ✅ Create or Update Event
    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {

        // Event savedEvent = eventService.saveEvent(event);
        Event savedEvent = eventService.createEvent(event);

        return ResponseEntity.ok(savedEvent);
    }

           // ✅ Search Events by Keyword (title or venue)
        @GetMapping("/search")
          public ResponseEntity<List<Event>> searchEvents(@RequestParam("keyword") String keyword) {
          List<Event> results = eventService.searchEvents(keyword);
    if (results.isEmpty()) {
        return ResponseEntity.noContent().build(); // 204 No Content if nothing found
    }
    return ResponseEntity.ok(results);
}

    // ✅ Get All Events
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // ✅ Get Event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Event by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully ✅");
    }

    // ✅ Update Event
    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        Optional<Event> existingEvent = eventService.getEventById(id);

        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setEventTitle(updatedEvent.getEventTitle());
            event.setDate(updatedEvent.getDate());
            event.setVenue(updatedEvent.getVenue());
            event.setSpeaker(updatedEvent.getSpeaker());
            event.setDescription(updatedEvent.getDescription());

            // Event savedEvent = eventService.saveEvent(event);
            Event savedEvent = eventService.createEvent(event);
            return ResponseEntity.ok(savedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }

        


 

}
}
