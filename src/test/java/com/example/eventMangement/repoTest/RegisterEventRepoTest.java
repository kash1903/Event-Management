package com.example.eventMangement.repoTest;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.eventMangement.model.RegisterEvent;
import com.example.eventMangement.repo.RegisterEventRepo;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;

public class RegisterEventRepoTest {
    
     @Autowired
    private RegisterEventRepo registerEventRepo;

    @Test
    void testFindByEventId() {
        RegisterEvent regEvent = new RegisterEvent();
        // regEvent.setEventId(100L);
        regEvent.setEmail("test@example.com");
        regEvent.setUsername("John");
        registerEventRepo.save(regEvent);

        List<RegisterEvent> events = registerEventRepo.findByEventId(100L);

        assertThat(events).isNotEmpty();
        assertThat(events.get(0).getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testFindByEmailAndEventId() {
        RegisterEvent regEvent = new RegisterEvent();
        // regEvent.setEventId(200L);
        regEvent.setEmail("user@example.com");
        regEvent.setUsername("Alice");
        registerEventRepo.save(regEvent);

        Optional<RegisterEvent> found = registerEventRepo.findByEmailAndEventId("user@example.com", 200L);

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("Alice");
}
}
