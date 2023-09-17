package pl.giczewski.demo.api.github.mapper;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.giczewski.demo.api.github.exception.NoSuchRequiredValuesFromGitHubApi;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;
import pl.giczewski.demo.api.github.utils.TestVariables;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JsonToResponseGitHubApiMapperTest {

    @InjectMocks
    private JsonToResponseGitHubApiMapper sut;

    @Test
    void shouldBeMappedFromJsonObjectToApiResponse() {
        //given
        JSONObject jsonObject = new JSONObject(createMapForJsonObject());

        //when
        GitHubApiResponse gitHubApiResponse = sut.mapJsonObjectToResponse(jsonObject);

        //then
        assertEquals(TestVariables.TEST_VALUE_ID, gitHubApiResponse.getId());
        assertEquals(TestVariables.TEST_VALUE_LOGIN, gitHubApiResponse.getLogin());
        assertEquals(TestVariables.TEST_VALUE_NAME, gitHubApiResponse.getName());
        assertEquals(TestVariables.TEST_VALUE_TYPE, gitHubApiResponse.getType());
        assertEquals(TestVariables.TEST_VALUE_AVATAR_URL, gitHubApiResponse.getAvatarUrl());
        assertEquals(TestVariables.TEST_VALUE_CREATED_AT, gitHubApiResponse.getCreatedAt());
        assertEquals(TestVariables.TEST_VALUE_CALCULATIONS, gitHubApiResponse.getCalculations());
    }

    @Test
    void shouldThrowExceptionWhenIsNoValueInJsonFromApi() {
        assertThrows(NoSuchRequiredValuesFromGitHubApi.class,
                () -> sut.mapJsonObjectToResponse(new JSONObject(createMapForJsonObjectWithNoValueForOneKey()))
        );
    }

    private static Map<String, String> createMapForJsonObject() {
        Map<String, String> values = new HashMap<>();
        values.put("id", TestVariables.TEST_VALUE_ID);
        values.put("login", TestVariables.TEST_VALUE_LOGIN);
        values.put("name", TestVariables.TEST_VALUE_NAME);
        values.put("type", TestVariables.TEST_VALUE_TYPE);
        values.put("avatar_url", TestVariables.TEST_VALUE_AVATAR_URL);
        values.put("created_at", TestVariables.TEST_VALUE_CREATED_AT);
        values.put("followers", TestVariables.TEST_VALUE_FOLLOWERS);
        values.put("public_gists", TestVariables.TEST_VALUE_PUBLIC_GISTS);
        values.put("calculations", TestVariables.TEST_VALUE_CALCULATIONS);
        return values;
    }

    private static Map<String, String> createMapForJsonObjectWithNoValueForOneKey() {
        Map<String, String> values = new HashMap<>();
        values.put("id", TestVariables.TEST_VALUE_ID);
        values.put("name", TestVariables.TEST_VALUE_NAME);
        values.put("type", TestVariables.TEST_VALUE_TYPE);
        values.put("avatar_url", TestVariables.TEST_VALUE_AVATAR_URL);
        values.put("created_at", TestVariables.TEST_VALUE_CREATED_AT);
        values.put("followers", TestVariables.TEST_VALUE_FOLLOWERS);
        values.put("public_gists", TestVariables.TEST_VALUE_PUBLIC_GISTS);
        values.put("calculations", TestVariables.TEST_VALUE_CALCULATIONS);
        return values;
    }
}