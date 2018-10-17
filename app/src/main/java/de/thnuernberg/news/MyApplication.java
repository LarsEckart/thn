package de.thnuernberg.news;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import de.thnuernberg.news.di.AppComponent;
import de.thnuernberg.news.di.DaggerAppComponent;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class MyApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
    }

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
