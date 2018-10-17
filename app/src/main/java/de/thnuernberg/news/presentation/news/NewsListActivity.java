package de.thnuernberg.news.presentation.news;

import android.os.Bundle;

import dagger.android.DaggerActivity;
import de.thnuernberg.news.R;
import timber.log.Timber;

public class NewsListActivity extends DaggerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_newslist);
    }

}

