package de.thnuernberg.news.data.source;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.remote.RemoteNewsDataSource;
import org.junit.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewsEntryRepository_should {

    @Test
    public void load_news_from_remote_data_source() throws Exception {
        // given
        RemoteNewsDataSource remoteDataSource = mock(RemoteNewsDataSource.class);
        NewsRepository newsRepository = new NewsRepository(remoteDataSource);

        // when
        newsRepository.getNews(new LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<NewsEntry> newsEntries) {

            }
        });

        // then
        verify(remoteDataSource).getNews(any(LoadNewsCallback.class));
    }
}
