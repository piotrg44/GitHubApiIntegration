package pl.giczewski.demo.api.github.exception;


public class NoSuchRequiredValuesFromGitHubApi extends RuntimeException {

    public NoSuchRequiredValuesFromGitHubApi(String message) {
        super(message);
    }
}
