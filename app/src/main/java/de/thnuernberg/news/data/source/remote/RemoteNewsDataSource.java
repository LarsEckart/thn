package de.thnuernberg.news.data.source.remote;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.NewsDataSource;
import de.thnuernberg.news.data.source.LoadNewsCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class RemoteNewsDataSource implements NewsDataSource {

    private final String url;
    private final RemoteDataSourceResponseConverter converter;
    private final OkHttpClient client;

    @Inject
    public RemoteNewsDataSource(String url, RemoteDataSourceResponseConverter converter, OkHttpClient client) {
        this.url = url;
        this.converter = converter;
        this.client = client;
    }

    @Override
    public void getNews(final LoadNewsCallback callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/xml")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Timber.e(e.getMessage());
                List<NewsEntry> newsEntries = Collections.emptyList();
                callback.onNewsLoaded(newsEntries);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    List<NewsEntry> newsEntries = Collections.emptyList();
                    callback.onNewsLoaded(newsEntries);
                }
                try (ResponseBody responseBody = response.body()) {
                    InputStream inputStream = responseBody.byteStream();
                    List<NewsEntry> newsEntries = converter.convert(inputStream);
                    Timber.i("found " + newsEntries.size() + " news entries");

                    callback.onNewsLoaded(newsEntries);
                }
            }
        });
    }

}
