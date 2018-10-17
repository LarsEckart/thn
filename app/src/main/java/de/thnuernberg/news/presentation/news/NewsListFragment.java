package de.thnuernberg.news.presentation.news;

import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import dagger.android.AndroidInjection;
import de.thnuernberg.news.R;
import de.thnuernberg.news.data.NewsEntry;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NewsListFragment extends ListFragment implements NewsListView {

    private static final String BUNDLE_NEWS_LIST_KEY = "newsListKey";

    @Inject
    NewsListPresenter presenter;

    private List<NewsEntry> newsEntries = new ArrayList<NewsEntry>();
    private NewsEntryAdapter adapter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("onActivityCreated");
        if (savedInstanceState != null) {
            Timber.d("its not null");
        }
        this.presenter.start();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.d("onSaveInstanceState");
    }

    @Override
    public void showLoading() {
        this.setListShown(false);
    }

    @Override
    public void showNewsEntries(final List<NewsEntry> newsEntries) {
        this.newsEntries = newsEntries;
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setListShown(true);
                adapter = new NewsEntryAdapter(getActivity(), R.layout.row, newsEntries);
                FragmentManager fm = getFragmentManager();
                ListFragment newsListFragment = (NewsListFragment) fm.findFragmentById(R.id.NewsListFragment);
                newsListFragment.setListAdapter(adapter);
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Timber.d("onListItemClick");

        Toast.makeText(getActivity(), "news entry clicked", Toast.LENGTH_SHORT).show();
        // https://developer.android.com/guide/components/intents-common
    }
}
