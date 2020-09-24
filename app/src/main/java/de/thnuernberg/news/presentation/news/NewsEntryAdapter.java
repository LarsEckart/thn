package de.thnuernberg.news.presentation.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.thnuernberg.news.R;
import de.thnuernberg.news.data.NewsEntry;

import java.util.List;

public class NewsEntryAdapter extends ArrayAdapter<NewsEntry> {

    private List<NewsEntry> newsEntries;

    private int layoutId;

    public NewsEntryAdapter(Context context, int layoutId, List<NewsEntry> items) {
        super(context, layoutId, items);
        newsEntries = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return newsEntries.size();
    }

    @Override
    public NewsEntry getItem(int index) {
        return newsEntries.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row = inflater.inflate(layoutId, parent, false);
        }

        ViewHolder holder = (ViewHolder) row.getTag();
        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        NewsEntry newsEntry = newsEntries.get(position);

        // TODO: bug, why do we show the long description like its the title?
        // TODO: we have to show title, description and date, see res/layout/row.xml
        holder.newsTitle.setText(newsEntry.getDescription());

        LinearLayout ll = row.findViewById(R.id.newsLinearLayout);

        if (position % 2 == 0) {
            ll.setBackgroundResource(R.color.white);
        } else {
            // TODO: let's alternate 2 different background colours. Maybe something grey?
            ll.setBackgroundResource(R.color.white);
        }
        return row;
    }

    static class ViewHolder {

        TextView newsTitle;
        TextView newsDescription;
        TextView pubDateTextView;

        ViewHolder(View row) {
            this.newsTitle = row.findViewById(R.id.newsTitle);
            this.newsDescription = row.findViewById(R.id.newsDescription);
            this.pubDateTextView = row.findViewById(R.id.dateView);
        }
    }
}
