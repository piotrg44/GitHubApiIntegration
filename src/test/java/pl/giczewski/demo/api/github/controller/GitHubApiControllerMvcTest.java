package pl.giczewski.demo.api.github.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.giczewski.demo.api.github.model.dto.GitHubApiResponse;
import pl.giczewski.demo.api.github.service.GitHubApiService;
import pl.giczewski.demo.api.github.utils.TestVariables;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GitHubApiController.class)
class GitHubApiControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GitHubApiService service;

    @Test
    void shouldReturnCorectResponse() throws Exception {
        //given
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/users/piotrg44").contentType(MediaType.APPLICATION_JSON);

        when(service.getUserDetails("piotrg44")).thenReturn(createGitHubApiResponse());

        //when
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TestVariables.TEST_VALUE_ID))
                .andExpect(jsonPath("$.login").value(TestVariables.TEST_VALUE_LOGIN))
                .andExpect(jsonPath("$.name").value(TestVariables.TEST_VALUE_NAME))
                .andExpect(jsonPath("$.type").value(TestVariables.TEST_VALUE_TYPE))
                .andExpect(jsonPath("$.avatarUrl").value(TestVariables.TEST_VALUE_AVATAR_URL))
                .andExpect(jsonPath("$.createdAt").value(TestVariables.TEST_VALUE_CREATED_AT))
                .andExpect(jsonPath("$.calculations").value(TestVariables.TEST_VALUE_CALCULATIONS));
    }

    private GitHubApiResponse createGitHubApiResponse() {
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