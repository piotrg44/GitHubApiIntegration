package pl.giczewski.demo.api.github.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.giczewski.demo.api.github.model.db.RequestCounter;
import pl.giczewski.demo.api.github.repository.RequestCounterRepository;
import pl.giczewski.demo.api.github.utils.TestVariables;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestCounterServiceTest {

    private static final long TEST_VALUE_ID = 1;
    private static final String TEST_VALUE_LOGIN = "piotrg44";
    private static final long TEST_VALUE_REQUEST_COUNT = 5;

    @Mock
    RequestCounterRepository requestCounterRepository;

    @InjectMocks
    private RequestCounterService sut;

    @Test
    void shouldInvokeMethodFromRepository() {
        //given
        var gitHubApiResponse = TestVariables.createGitHubApiResponse();
        RequestCounter existingCounter = new RequestCounter(TEST_VALUE_ID, TEST_VALUE_LOGIN, TEST_VALUE_REQUEST_COUNT);
        when(requestCounterRepository.findByLogin(gitHubApiResponse.getLogin())).thenReturn(Optional.of(existingCounter));
        when(requestCounterRepository.save(any(RequestCounter.class))).thenReturn(existingCounter);

        //when
        sut.increaseRequestAmount(gitHubApiResponse);

        //then
        verify(requestCounterRepository).save(existingCounter.increaseCounter());
    }

    @Test
    void shouldInvokeSaveMethodFromRepositoryWithObjectFromDatabase(){
        //given
        var gitHubApiResponse = TestVariables.createGitHubApiResponse();

        //when
        sut.increaseRequestAmount(gitHubApiResponse);

        //then
        verify(requestCounterRepository).findByLogin(TestVariables.TEST_VALUE_LOGIN);
    }
}