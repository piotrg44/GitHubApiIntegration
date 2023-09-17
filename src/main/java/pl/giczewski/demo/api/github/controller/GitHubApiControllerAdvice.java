package pl.giczewski.demo.api.github.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.giczewski.demo.api.github.exception.NoSuchRequiredValuesFromGitHubApiException;
import pl.giczewski.demo.api.github.exception.UserNotFoundException;
import pl.giczewski.demo.api.github.model.ResponseMessage;

@RestControllerAdvice
public class GitHubApiControllerAdvice {

    @ExceptionHandler(value = NoSuchRequiredValuesFromGitHubApiException.class)
    private ResponseEntity<ResponseMessage> noSuchRequiredValuesFromGitHubApiExceptionHandler(NoSuchRequiredValuesFromGitHubApiException ex) {
        ResponseMessage responseMessage = new ResponseMessage(ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    private ResponseEntity<ResponseMessage> userNotFoundExceptionHandler(UserNotFoundException ex) {
        ResponseMessage responseMessage = new ResponseMessage(ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
}
