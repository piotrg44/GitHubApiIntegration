package pl.giczewski.demo.api.github.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;
import pl.giczewski.demo.api.github.model.db.RequestCounter;
import pl.giczewski.demo.api.github.repository.RequestCounterRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestCounterService {

    private final RequestCounterRepository requestCounterRepository;

    public void increaseRequestAmount(GitHubApiResponse gitHubApiResponse) {
        Optional<RequestCounter> requestCounter = requestCounterRepository.findByLogin(gitHubApiResponse.getLogin());
        if (requestCounter.isEmpty()) {
            saveNewCouter(gitHubApiResponse);
        } else {
            requestCounterRepository.save(requestCounter.get().increaseCounter());
        }
    }

    private RequestCounter saveNewCouter(GitHubApiResponse gitHubApiResponse) {
        return requestCounterRepository.save(
                RequestCounter.builder()
                        .requestCount(1)
                        .login(gitHubApiResponse.getLogin())
                        .build());
    }
}
