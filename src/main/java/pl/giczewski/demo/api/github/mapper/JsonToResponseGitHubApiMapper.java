package pl.giczewski.demo.api.github.mapper;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import pl.giczewski.demo.api.github.exception.NoSuchRequiredValuesFromGitHubApi;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;

@Component
@Slf4j
public class JsonToResponseGitHubApiMapper {

    public GitHubApiResponse mapJsonObjectToResponse(JSONObject gitHubApiResponseObject) {
        try {
            return GitHubApiResponse.builder()
                    .id(Long.toString(gitHubApiResponseObject.getLong("id")))
                    .login(gitHubApiResponseObject.getString("login"))
                    .name(gitHubApiResponseObject.getString("name"))
                    .type(gitHubApiResponseObject.getString("type"))
                    .avatarUrl(gitHubApiResponseObject.getString("avatar_url"))
                    .createdAt(gitHubApiResponseObject.getString("created_at"))
                    .calculations(calculateResponse(gitHubApiResponseObject.getInt("followers"), gitHubApiResponseObject.getInt("public_gists")))
                    .build();
        } catch (JSONException e) {
            log.error("Not such required json values in response", e);
            throw new NoSuchRequiredValuesFromGitHubApi("One of the response values from GitHub API is not correct");
        }
    }

    private String calculateResponse(int followers, int publicRepos) {
        return Double.toString(6.0 / followers * (2.0 + publicRepos));
    }
}
