package com.example.eventMangement.model;

import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventTitle;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String venue;

    private String speaker;

    @Column(length = 1000)
    private String description;

    // @Column(nullable = false)
    private Long adminId; // ID of the admin who created the event

        // ✅ Add this mapping for cascade delete
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AttendEvent> attendees;


    // ✅ Cascade delete for event registrations
@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference(value = "register-event")
private List<RegisterEvent> registrations;

}
