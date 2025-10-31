package com.example.eventMangement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class AttendEvent {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String feedback;

    // Link to Event (One event can have many attendees)
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;
}
