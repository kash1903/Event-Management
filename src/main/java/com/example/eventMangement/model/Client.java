package com.example.eventMangement.model;

import jakarta.persistence.*;
import lombok.*;




@Entity
@Table(name = "client")
@Data // generates getters, setters, toString, equals, hashCode automatically
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "CLIENT" or "ADMIN"

}
