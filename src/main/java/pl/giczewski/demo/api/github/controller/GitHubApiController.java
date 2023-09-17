package pl.giczewski.demo.api.github.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.giczewski.demo.api.github.exception.NoSuchRequiredValuesFromGitHubApi;
import pl.giczewski.demo.api.github.exception.UserNotFoundException;
import pl.giczewski.demo.api.github.model.ResponseMessage;
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

    @ExceptionHandler(value = NoSuchRequiredValuesFromGitHubApi.class)
    private ResponseEntity<ResponseMessage> noSuchRequiredValuesFromGitHubApiExceptionHandler(NoSuchRequiredValuesFromGitHubApi ex) {
        ResponseMessage responseMessage = new ResponseMessage(ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    private ResponseEntity<ResponseMessage> userNotFoundExceptionHandler(UserNotFoundException ex) {
        ResponseMessage responseMessage = new ResponseMessage(ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
}

