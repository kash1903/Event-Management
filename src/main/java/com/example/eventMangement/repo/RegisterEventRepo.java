package com.example.eventMangement.repo;

import com.example.eventMangement.model.RegisterEvent;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterEventRepo extends JpaRepository<RegisterEvent,Long> {
    
     List<RegisterEvent> findByEventId(Long eventId);
    Optional<RegisterEvent> findByEmailAndEventId(String email, Long eventId);
}
