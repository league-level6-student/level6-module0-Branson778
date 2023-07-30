package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;

    @Mock
    WebClient webMock;

    @Mock
    Mono<ApiExampleWrapper> aewMono;

    @Mock
    WebClient.RequestHeadersUriSpec rhusMock;

    @Mock
    WebClient.RequestHeadersSpec rhsMock;

    @Mock
    WebClient.ResponseSpec rsMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
   newsApi = new NewsApi();
   newsApi.setWebClient(webMock);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
        String topic = "Bread";

        ApiExampleWrapper aew = new ApiExampleWrapper();


        when(webMock.get()).thenReturn(rhusMock);
        when(rhusMock.uri((Function<UriBuilder, URI>) any())).thenReturn(rhsMock);
        when(rhsMock.retrieve()).thenReturn(rsMock);
        when(rsMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(aewMono);
        when(aewMono.block()).thenReturn(aew);
        //when
       ApiExampleWrapper actualResults = newsApi.getNewsStoryByTopic(topic);
        //then
        verify(webMock, times(1)).get();
        assertEquals(aew, actualResults);

    }

    @Test
    void itShouldFindStory(){
        //given
        String topic = "Bread";
        //when

        //then
    }


}