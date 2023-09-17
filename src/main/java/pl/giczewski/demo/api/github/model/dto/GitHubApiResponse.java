package pl.giczewski.demo.api.github.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GitHubApiResponse {
    private String id;

    private String login;

    private String name;

    private String type;

    private String avatarUrl;

    private String createdAt;

    private String calculations;
}


