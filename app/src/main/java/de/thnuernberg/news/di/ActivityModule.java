package de.thnuernberg.news.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.thnuernberg.news.presentation.news.NewsListActivity;
import de.thnuernberg.news.presentation.news.NewsListActivityModule;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = NewsListActivityModule.class)
    abstract NewsListActivity bindNewsListActivity();

}
