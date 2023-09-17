package pl.giczewski.demo.api.github.utils;

import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;

public class TestVariables {
    public final static String TEST_VALUE_ID = "62173542";
    public final static String TEST_VALUE_LOGIN = "piotrg44";
    public final static String TEST_VALUE_NAME = "Piotr Giczewski";
    public final static String TEST_VALUE_TYPE = "User";
    public final static String TEST_VALUE_AVATAR_URL = "https://avatars.githubusercontent.com/u/62173542?v=4";
    public final static String TEST_VALUE_CREATED_AT = "2020-03-14T10:40:40Z";
    public final static String TEST_VALUE_FOLLOWERS = "5.0";
    public final static String TEST_VALUE_PUBLIC_GISTS = "2.0";
    public final static String TEST_VALUE_CALCULATIONS = Double.toString(6.0 / Double.parseDouble(TEST_VALUE_FOLLOWERS) * (2.0 + Double.parseDouble(TEST_VALUE_PUBLIC_GISTS)));

    public static GitHubApiResponse createGitHubApiResponse() {
        return GitHubApiResponse.builder()
                .id(TestVariables.TEST_VALUE_ID)
                .login(TestVariables.TEST_VALUE_LOGIN)
                .name(TestVariables.TEST_VALUE_NAME)
                .type(TestVariables.TEST_VALUE_TYPE)
                .avatarUrl(TestVariables.TEST_VALUE_AVATAR_URL)
                .createdAt(TestVariables.TEST_VALUE_CREATED_AT)
                .calculations(TestVariables.TEST_VALUE_CALCULATIONS)
                .build();
    }
}
