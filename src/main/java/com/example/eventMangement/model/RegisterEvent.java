package com.example.eventMangement.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RegisterEvent {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // Link to Event (One event can have many registrations)
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference(value = "register-event")
    private Event event;
}
