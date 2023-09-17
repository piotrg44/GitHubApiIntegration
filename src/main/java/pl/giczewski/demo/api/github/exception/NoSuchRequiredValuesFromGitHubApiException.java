package pl.giczewski.demo.api.github.exception;


public class NoSuchRequiredValuesFromGitHubApiException extends RuntimeException {

    public NoSuchRequiredValuesFromGitHubApiException(String message) {
        super(message);
    }
}
