package de.thnuernberg.news.presentation.news;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsListFragmentModule {

    @Provides
    NewsListView provideNewsListView(NewsListFragment fragment) {
        return fragment;
    }

    @Provides
    NewsListPresenter provideNewsListPresenter(final NewsListPresenterImpl presenter) {
        return presenter;
    }
}
