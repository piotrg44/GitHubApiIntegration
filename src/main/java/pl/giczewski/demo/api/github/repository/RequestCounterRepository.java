package pl.giczewski.demo.api.github.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.giczewski.demo.api.github.model.db.RequestCounter;

import java.util.Optional;

@Repository
public interface RequestCounterRepository extends CrudRepository<RequestCounter, Long> {
    Optional<RequestCounter> findByLogin(String login);
}
