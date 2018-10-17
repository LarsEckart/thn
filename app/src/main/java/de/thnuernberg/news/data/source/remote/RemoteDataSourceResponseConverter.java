package de.thnuernberg.news.data.source.remote;

import de.thnuernberg.news.data.NewsEntry;

import java.io.InputStream;
import java.util.List;

public interface RemoteDataSourceResponseConverter {

    public List<NewsEntry> convert(InputStream inputStream);
}
