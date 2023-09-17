package pl.giczewski.demo.api.github.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.giczewski.demo.api.github.exception.UserNotFoundException;
import pl.giczewski.demo.api.github.mapper.JsonMapper;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubApiService {
    private static final String GITHUB_URL_USERS = "https://api.github.com/users/";

    private final RequestCounterService requestCounterService;
    private final JsonMapper jsonToResponseGitHubApiMapper;
    private final RestTemplate restTemplate;

    @Transactional
    public GitHubApiResponse getUserDetails(String login) {
        try {
            GitHubApiResponse gitHubApiResponse = createGitHubResponseFromGitHubApi(login);
            requestCounterService.increaseRequestAmount(gitHubApiResponse);
            return gitHubApiResponse;
        } catch (HttpClientErrorException.NotFound e) {
            log.error("User was not found", e);
            throw new UserNotFoundException("User with login " + login + " was not found");
        }
    }

    private GitHubApiResponse createGitHubResponseFromGitHubApi(String login) {
        String gitHubApiResponseString = restTemplate.getForObject(GITHUB_URL_USERS + login, String.class);
        JSONObject gitHubApiResponseObject = new JSONObject(gitHubApiResponseString);
        return jsonToResponseGitHubApiMapper.mapGitHubApiResponseJsonObjectToResponse(gitHubApiResponseObject);
    }
}
