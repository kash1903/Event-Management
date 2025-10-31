package com.example.eventMangement.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class AdminService {
    private final String BASE_URL = "http://localhost:8080/api/events";
    private final String STATS_URL = "http://localhost:8080/api/event";
    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Fetch all events
    public List<Map<String, Object>> getAllEvents() {
        return Arrays.asList(restTemplate.getForObject(BASE_URL + "/all", Map[].class));
    }

    // ✅ Search events
    public List<Map<String, Object>> searchEvents(String keyword) {
        return Arrays.asList(restTemplate.getForObject(BASE_URL + "/search?keyword=" + keyword, Map[].class));
    }

    // ✅ Create event
    public Map<String, Object> createEvent(Map<String, Object> eventData) {
        return restTemplate.postForObject(BASE_URL + "/create", eventData, Map.class);
    }

    // // ✅ Update event
    // public void updateEvent(Long id, Map<String, Object> eventData) {
    //     restTemplate.put(BASE_URL + "/update/" + id, eventData);
    // }

    // ✅ Delete event
    public void deleteEvent(Long id) {
        restTemplate.delete(BASE_URL + "/delete/" + id);
    }

    // ✅ Get stats for a specific event
    public Map<String, Object> getEventStats(Long eventId) {
        return restTemplate.getForObject(STATS_URL + "/" + eventId + "/stats", Map.class);
    }
}
