package de.thnuernberg.news.presentation.news;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.LoadNewsCallback;
import de.thnuernberg.news.data.source.NewsRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NewsListPresenterImpl_should {

    private NewsListView view;
    private NewsRepository repository;

    private NewsListPresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(NewsListView.class);
        repository = mock(NewsRepository.class);
        presenter = new NewsListPresenterImpl(view, repository);
    }

    @Test
    public void display_loading_indicator_when_start() throws Exception {
        // given

        // when
        presenter.start();

        // then
        verify(view).showLoading();
    }

    @Test
    public void query_news_from_repository() throws Exception {
        // given

        // when
        presenter.start();

        // then
        verify(repository).getNews(any(LoadNewsCallback.class));
    }

    @Test
    public void tell_view_on_news_loaded_callback_to_display_news_entries() throws Exception {
        // given
        List<NewsEntry> newsEntries = new ArrayList<>();

        // when
        presenter.onNewsLoaded(newsEntries);

        // then
        verify(view).showNewsEntries(newsEntries);
    }
}
