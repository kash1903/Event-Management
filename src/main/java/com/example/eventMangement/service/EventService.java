package com.example.eventMangement.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.eventMangement.repo.EventRepo;
import com.example.eventMangement.model.Event;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service 
public class EventService {
    
        @Autowired
    private EventRepo eventRepo;

    // 🔹 Get all events
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    // 🔹 Get event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

   // 🔹 Search events by title or venue
public List<Event> searchEvents(String keyword) {
    return eventRepo.findByEventTitleContainingIgnoreCaseOrVenueContainingIgnoreCase(keyword, keyword);
}




    // 🔹 Add new event
    public Event createEvent(Event event) {
        return eventRepo.save(event);
    }

    // 🔹 Update existing event
    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepo.findById(id);

        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setEventTitle(updatedEvent.getEventTitle());
            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setVenue(updatedEvent.getVenue());
            existingEvent.setSpeaker(updatedEvent.getSpeaker());
            existingEvent.setDescription(updatedEvent.getDescription());
            return eventRepo.save(existingEvent);
        } else {
            throw new RuntimeException("Event not found with ID: " + id);
        }
    }

    // 🔹 Delete event by ID
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }


    // Find who created this Event 
    public List<Event> getEventsByAdmin(Long adminId) {
    return eventRepo.findByAdminId(adminId);
}

}
