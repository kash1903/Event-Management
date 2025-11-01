package com.example.eventMangement.repoTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.eventMangement.model.Client;
import com.example.eventMangement.repo.ClientRepo;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientRepoTest {
      @Autowired
    private ClientRepo clientRepo;

    @Test
    void testRepositoryLoads() {
        assertThat(clientRepo).isNotNull();
    }

    @Test
    void testFindByUsernameAndPassword() {
        // given
        Client client = new Client();
        client.setUsername("john");
        client.setUserEmail("john@example.com");
        client.setPassword("1234");
        clientRepo.save(client);

        // when
        Client found = clientRepo.findByUsernameAndPassword("john", "1234");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getUserEmail()).isEqualTo("john@example.com");
    }
}
