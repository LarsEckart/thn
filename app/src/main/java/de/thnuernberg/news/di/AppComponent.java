package de.thnuernberg.news.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import de.thnuernberg.news.MyApplication;

import javax.inject.Singleton;

/**
 * root of the dagger graph !
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        FragmentModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MyApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
