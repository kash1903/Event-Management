package com.example.eventMangement.repoTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.eventMangement.repo.AttendEventRepo;
import static org.assertj.core.api.Assertions.assertThat;

public class AttendEventRepoTest {
      @Autowired
    private AttendEventRepo attendEventRepo;

    @Test
    void testRepositoryLoads() {
        assertThat(attendEventRepo).isNotNull();
    }
}
