package de.thnuernberg.news.data.source;

import de.thnuernberg.news.data.NewsEntry;

import java.util.List;

public interface LoadNewsCallback {

    void onNewsLoaded(List<NewsEntry> newsEntries);
}
