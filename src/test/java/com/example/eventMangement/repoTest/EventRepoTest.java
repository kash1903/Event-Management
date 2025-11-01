package com.example.eventMangement.repoTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.eventMangement.model.Event;
import com.example.eventMangement.repo.EventRepo;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class EventRepoTest {
        @Autowired
    private EventRepo eventRepo;

    @Test
    void testFindByAdminId() {
        Event event = new Event();
        event.setEventTitle("Tech Talk");
        event.setVenue("Auditorium");
        event.setAdminId(1L);
        eventRepo.save(event);

        List<Event> events = eventRepo.findByAdminId(1L);

        assertThat(events).isNotEmpty();
        assertThat(events.get(0).getEventTitle()).isEqualTo("Tech Talk");
    }

    @Test
    void testFindByEventTitleContainingIgnoreCase() {
        Event event = new Event();
        event.setEventTitle("Spring Boot Workshop");
        event.setVenue("Hall 2");
        eventRepo.save(event);

        List<Event> found = eventRepo.findByEventTitleContainingIgnoreCase("spring");

        assertThat(found).isNotEmpty();
    }
}
