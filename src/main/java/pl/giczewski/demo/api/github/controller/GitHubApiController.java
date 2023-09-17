package pl.giczewski.demo.api.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;
import pl.giczewski.demo.api.github.service.GitHubApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class GitHubApiController {

    private final GitHubApiService gitHubApiService;

    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GitHubApiResponse> getSpecificInformationAboutUser(@PathVariable String login) {
        return ResponseEntity.ok(gitHubApiService.getUserDetails(login));
    }
}

