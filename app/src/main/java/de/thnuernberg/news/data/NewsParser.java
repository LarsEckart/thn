package de.thnuernberg.news.data;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;
import de.thnuernberg.news.data.source.remote.RemoteDataSourceResponseConverter;
import org.xml.sax.SAXException;
import timber.log.Timber;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsParser implements RemoteDataSourceResponseConverter {

    private static final String RSS = "rss";
    private static final String CHANNEL = "channel";
    private static final String ITEM = "item";

    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String PUB_DATE = "pubDate";
    private static final String DESCRIPTION = "description";

    @Override
    public List<NewsEntry> convert(InputStream in) {

        final List<NewsEntry> newsEntries = new ArrayList<NewsEntry>();
        final NewsEntry currentNewsEntry = new NewsEntry();

        // parser definition
        RootElement root = new RootElement(RSS);
        Element channel = root.getChild(CHANNEL);
        Element item = channel.getChild(ITEM);

        item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentNewsEntry.setTitle(body);
            }
        });
        item.getChild(LINK).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentNewsEntry.setLink(body);
            }
        });
        item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentNewsEntry.setPubDate(body);
            }
        });
        item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentNewsEntry.setDescription(body);
            }
        });
        item.setEndElementListener(new EndElementListener() {
            public void end() {
                newsEntries.add(NewsEntry.copyOf(currentNewsEntry));
            }
        });

        try {
            Xml.parse(in, Xml.Encoding.UTF_8, root.getContentHandler());
            return newsEntries;
        } catch (IOException e) {
            Timber.e(e.getMessage());
            return Collections.emptyList();
        } catch (SAXException e) {
            Timber.e(e.getMessage());
            return Collections.emptyList();
        }
    }
}
