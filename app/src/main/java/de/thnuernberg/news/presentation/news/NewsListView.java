package de.thnuernberg.news.presentation.news;


import de.thnuernberg.news.data.NewsEntry;

import java.util.List;

public interface NewsListView {

    void showLoading();

    void showNewsEntries(List<NewsEntry> newsEntries);
}
