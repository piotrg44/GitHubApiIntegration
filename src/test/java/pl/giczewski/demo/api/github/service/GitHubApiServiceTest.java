package pl.giczewski.demo.api.github.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.giczewski.demo.api.github.exception.UserNotFoundException;
import pl.giczewski.demo.api.github.mapper.JsonMapper;
import pl.giczewski.demo.api.github.utils.TestVariables;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubApiServiceTest {

    @Mock
    private RequestCounterService requestCounterService;

    @Mock
    private JsonMapper jsonToResponseGitHubApiMapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GitHubApiService sut;

    @Test
    void shouldReturnCorrectResponse() {
        //given
        String apiUrl = "https://api.github.com/users/" + TestVariables.TEST_VALUE_LOGIN;
        String jsonResponse = "{ \"login\": \"" + TestVariables.TEST_VALUE_LOGIN + "\", \"name\": \"" + "Test User" + "\" }";
        var expectedResponse = TestVariables.createGitHubApiResponse();
        when(restTemplate.getForObject(apiUrl, String.class)).thenReturn(jsonResponse);
        when(jsonToResponseGitHubApiMapper.mapGitHubApiResponseJsonObjectToResponse(any()))
                .thenReturn(expectedResponse);

        //when
        var result = sut.getUserDetails(TestVariables.TEST_VALUE_LOGIN);

        //then
        assertEquals(expectedResponse, result);
        verify(requestCounterService).increaseRequestAmount(expectedResponse);
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //given
        String login = "nonexistentuser";
        String apiUrl = "https://api.github.com/users/" + login;
        when(restTemplate.getForObject(apiUrl, String.class)).thenThrow(HttpClientErrorException.NotFound.class);

        //when
        assertThrows(UserNotFoundException.class, () -> sut.getUserDetails(login));

        //then
        verify(requestCounterService, never()).increaseRequestAmount(any());
    }
}