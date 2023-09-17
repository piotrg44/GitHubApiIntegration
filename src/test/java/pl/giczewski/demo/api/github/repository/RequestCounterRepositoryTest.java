package pl.giczewski.demo.api.github.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.giczewski.demo.api.github.model.db.RequestCounter;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RequestCounterRepositoryTest {
    @Autowired
    private RequestCounterRepository sut;

    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }

    @Test
    void shouldSaveRequestCounter() {
        sut.save(new RequestCounter(1L, "piotrg44", 1));
        Optional<RequestCounter> requestCounter = sut.findByLogin("piotrg44");
        assertTrue(requestCounter.isPresent());
        assertThat(requestCounter.get().getRequestCount()).isEqualTo(1L);
        assertThat(requestCounter.get().getLogin()).isEqualTo("piotrg44");
        assertThat(requestCounter.get().getId()).isEqualTo(1L);
    }
}