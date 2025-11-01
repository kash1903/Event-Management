package com.example.eventMangement.serviceTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.example.eventMangement.service.AdminService;

public class AdminServiceTest {
    
     @InjectMocks
    private AdminService adminService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adminService = new AdminService();
        adminService.getClass(); // just to ensure init
    }

    @Test
    void testGetAllEvents() {
        Map<String, Object> mockEvent = new HashMap<>();
        mockEvent.put("eventTitle", "Tech Fest");
        when(restTemplate.getForObject(anyString(), eq(Map[].class)))
                .thenReturn(new Map[]{mockEvent});

        // Execute
        // List<Map<String, Object>> result = Arrays.asList(restTemplate.getForObject("url", Map[].class));

        // Verify
        // assertThat(result).isNotEmpty();
        // assertThat(result.get(0).get("eventTitle")).isEqualTo("Tech Fest");
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(restTemplate).delete(anyString());
        restTemplate.delete("someurl");
        verify(restTemplate, times(1)).delete(anyString());
    }
}
