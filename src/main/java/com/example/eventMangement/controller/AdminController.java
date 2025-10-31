package com.example.eventMangement.controller;
import com.example.eventMangement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
      @Autowired
    private AdminService adminService;

    // ✅ Show admin dashboard
    @GetMapping
    public String showAdminPage(Model model) {
        List<Map<String, Object>> events = adminService.getAllEvents();
        model.addAttribute("events", events);
        return "admin";
    }

    // ✅ Search events
    @GetMapping("/search")
    public String searchEvents(@RequestParam String keyword, Model model) {
        List<Map<String, Object>> events = adminService.searchEvents(keyword);
        model.addAttribute("events", events);
        return "admin";
    }

    // ✅ Create event
    @PostMapping("/create")
    public String createEvent(@RequestParam Map<String, Object> eventData) {
        adminService.createEvent(eventData);
        return "redirect:/admin";
    }

    // ✅ Update event (Edit feature)
//     @PostMapping("/update/{id}")
//     public String updateEvent(@PathVariable Long id, @RequestParam Map<String, Object> eventData) {
//     adminService.updateEvent(id, eventData);
//     return "redirect:/admin";
// }


    // ✅ Delete event
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        adminService.deleteEvent(id);
        return "redirect:/admin";
    }

    // ✅ Show stats for a specific event
    @GetMapping("/stats/{eventId}")
    @ResponseBody
    public Map<String, Object> getEventStats(@PathVariable Long eventId) {
        return adminService.getEventStats(eventId);
    }
}
