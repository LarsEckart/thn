package de.thnuernberg.news.data.source;

public interface NewsDataSource {

    public void getNews(LoadNewsCallback callback);
}
