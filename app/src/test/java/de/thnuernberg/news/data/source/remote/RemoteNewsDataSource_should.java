package de.thnuernberg.news.data.source.remote;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.LoadNewsCallback;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoteNewsDataSource_should {

    private RemoteNewsDataSource remoteNewsDataSource;
    private RemoteDataSourceResponseConverter converter;
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        String url = server.url("/news-archive").toString();

        converter = mock(RemoteDataSourceResponseConverter.class);

        remoteNewsDataSource = new RemoteNewsDataSource(url, converter, new OkHttpClient.Builder().build());
    }

    @Test
    public void execute_http_request_to_endpoint() throws Exception {
        // given
        server.enqueue(new MockResponse().setResponseCode(200).setBody("hello, world!"));

        // when
        remoteNewsDataSource.getNews(new LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsEntry> newsEntries) {
                assertThat(true).isFalse();
            }
        });

        // then
        RecordedRequest request = server.takeRequest(1000, TimeUnit.SECONDS);
        assertThat(request.getPath()).isEqualTo("/news-archive");
    }

    @Ignore("async http request not finished when evaluating the verify")
    @Test
    public void convert_response_to_our_model() throws Exception {
        // given
        setupMockResponseFromFile("reponse.xml");

        // when
        remoteNewsDataSource.getNews(new LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsEntry> newsEntries) {
            }
        });


        // then
        verify(converter).convert(any(InputStream.class));
    }

    private void setupMockResponseFromFile(String text) throws URISyntaxException, IOException {
        URI resource = this.getClass().getClassLoader().getResource(text).toURI();
        File file = new File(resource);
        InputStream targetStream = new FileInputStream(file);
        BufferedReader buf = new BufferedReader(new InputStreamReader(targetStream));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }

        String fileAsString = sb.toString();

        server.enqueue(new MockResponse().setBody(fileAsString).setResponseCode(200));
    }
}
