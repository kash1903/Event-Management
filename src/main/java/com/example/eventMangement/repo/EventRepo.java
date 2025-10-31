package com.example.eventMangement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventMangement.model.Event;

public interface EventRepo extends JpaRepository <Event,Long> {
    

   List<Event> findByAdminId(Long adminId); // ✅ new method

    // Search events by title (case-insensitive)
    List<Event> findByEventTitleContainingIgnoreCase(String keyword);

    // Search by venue
    List<Event> findByVenueContainingIgnoreCase(String venue);

    // Search by speaker
    List<Event> findBySpeakerContainingIgnoreCase(String speaker);

      List<Event> findByEventTitleContainingIgnoreCaseOrVenueContainingIgnoreCase(String titleKeyword, String venueKeyword);
}
