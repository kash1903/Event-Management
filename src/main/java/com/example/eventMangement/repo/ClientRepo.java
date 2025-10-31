package com.example.eventMangement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eventMangement.model.Client;

public interface ClientRepo extends JpaRepository <Client,Long> {


        // 🔥 Add this method for login
    Client findByUsernameAndPassword(String username, String password);
       // To login using email
    // Client findByEmailAndPassword(String userEmail, String password);
    Client findByUserEmailAndPassword(String userEmail, String password);


    
}
