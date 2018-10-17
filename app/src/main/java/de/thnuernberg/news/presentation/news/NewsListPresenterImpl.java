package de.thnuernberg.news.presentation.news;

import de.thnuernberg.news.data.NewsEntry;
import de.thnuernberg.news.data.source.NewsRepository;
import de.thnuernberg.news.data.source.LoadNewsCallback;

import java.util.List;
import javax.inject.Inject;

public class NewsListPresenterImpl implements NewsListPresenter, LoadNewsCallback {

    private final NewsListView view;
    private NewsRepository repository;

    @Inject
    public NewsListPresenterImpl(NewsListView view, NewsRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
        view.showLoading();
        repository.getNews(this);
    }

    @Override
    public void onNewsLoaded(List<NewsEntry> newsEntries) {
        view.showNewsEntries(newsEntries);
    }
}
