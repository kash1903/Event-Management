package com.example.eventMangement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventMangement.model.AttendEvent;

import java.util.List;

public interface AttendEventRepo extends JpaRepository<AttendEvent, Long> {

    List<AttendEvent> findByEventId(Long eventId);
}
