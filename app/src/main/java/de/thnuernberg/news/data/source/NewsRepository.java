package de.thnuernberg.news.data.source;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.remote.RemoteNewsDataSource;

import java.util.List;
import javax.inject.Inject;

public class NewsRepository implements NewsDataSource {

    private RemoteNewsDataSource remoteDataSource;

    @Inject
    public NewsRepository(RemoteNewsDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getNews(final LoadNewsCallback callback) {
        remoteDataSource.getNews(new LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsEntry> newsEntries) {
                callback.onNewsLoaded(newsEntries);
            }
        });
    }
}
