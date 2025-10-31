package com.example.eventMangement.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.eventMangement.model.Client;

@Controller
public class ClientUser {
    

      @GetMapping("/client")
    public String showClientPage(Model model) {
         Client client = new Client();  // or whatever your entity class is
   
    model.addAttribute("client", client);
        return "client"; // renders client.html
    }
}
