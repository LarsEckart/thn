package de.thnuernberg.news.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import de.thnuernberg.news.presentation.news.NewsListFragment;
import de.thnuernberg.news.presentation.news.NewsListFragmentModule;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector(modules = NewsListFragmentModule.class)
    abstract NewsListFragment bindNewsListFragment();
}
