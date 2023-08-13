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
import java.util.ArrayList;
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
        //Add articles to example wrapper
        ApiExampleWrapper aew = new ApiExampleWrapper();
        String title = "The Easiest Way to Prevent a Soggy Sandwich";
        String  content = "The only thing sadder than opening your lunchbox to find a soggy sandwich is opening your kids lunchbox at the end of the day to find a completely untouched one. Thankfully, you can prevent soggy, di… [+2289 chars]";
        String url = "https://lifehacker.com/make-sandwiches-on-frozen-bread-to-stave-off-sogginess-1803757453";

        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUrl(url);
        articles.add(article);

        aew.setArticles(articles);

        when(webMock.get()).thenReturn(rhusMock);
        when(rhusMock.uri((Function<UriBuilder, URI>) any())).thenReturn(rhsMock);
        when(rhsMock.retrieve()).thenReturn(rsMock);
        when(rsMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(aewMono);
        when(aewMono.block()).thenReturn(aew);
        //when
       ApiExampleWrapper actualResults = newsApi.getNewsStoryByTopic(topic);
        //then
        verify(webMock, times(1)).get();
        assertEquals(aew.getArticles().get(0), actualResults.getArticles().get(0));

    }

    @Test
    void itShouldFindStory(){
        //given
        ApiExampleWrapper aew = new ApiExampleWrapper();
        String topic = "Bread";
        String title = "The Easiest Way to Prevent a Soggy Sandwich";
        String  content = "The only thing sadder than opening your lunchbox to find a soggy sandwich is opening your kids lunchbox at the end of the day to find a completely untouched one. Thankfully, you can prevent soggy, di… [+2289 chars]";
        String url = "https://lifehacker.com/make-sandwiches-on-frozen-bread-to-stave-off-sogginess-1803757453";

        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUrl(url);
        articles.add(article);
        aew.setArticles(articles);

        when(webMock.get()).thenReturn(rhusMock);
        when(rhusMock.uri((Function<UriBuilder, URI>) any())).thenReturn(rhsMock);
        when(rhsMock.retrieve()).thenReturn(rsMock);
        when(rsMock.bodyToMono(ApiExampleWrapper.class)).thenReturn(aewMono);
        when(aewMono.block()).thenReturn(aew);

        String expectedResult =
                title + " -\n" + content + "\nFull article: " + url;
        //when
        String actualResult = newsApi.findStory(topic);
        //then
        verify(webMock, times(1)).get();
        assertEquals(expectedResult, actualResult);
    }


}