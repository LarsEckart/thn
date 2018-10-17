package de.thnuernberg.news.di;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import dagger.Module;
import dagger.Provides;
import de.thnuernberg.news.BuildConfig;
import de.thnuernberg.news.data.NewsParser;
import de.thnuernberg.news.data.source.NewsRepository;
import de.thnuernberg.news.data.source.remote.RemoteDataSourceResponseConverter;
import de.thnuernberg.news.data.source.remote.RemoteNewsDataSource;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    NewsRepository provideRepository(RemoteNewsDataSource ds) {
        return new NewsRepository(ds);
    }

    @Provides
    @Singleton
    RemoteNewsDataSource provideRemoteDataSource(
            @Named("baseUrl") String baseUrl,
            RemoteDataSourceResponseConverter converter,
            OkHttpClient okHttpClient) {
        return new RemoteNewsDataSource(baseUrl, converter, okHttpClient);
    }

    @Provides
    @Singleton
    RemoteDataSourceResponseConverter provideConverter() {
        return new NewsParser();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);

            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder.build();
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl() {
        return "https://www.th-nuernberg.de/news-archiv/rss.xml";
    }

}
