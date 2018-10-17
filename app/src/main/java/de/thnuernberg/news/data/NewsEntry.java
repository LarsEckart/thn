package de.thnuernberg.news.data;

public class NewsEntry {

    private String title;
    private String description;
    private String link;
    private String pubDate;

    public NewsEntry() {
    }

    public NewsEntry(String title, String description, String link, String pubDate) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public static NewsEntry copyOf(NewsEntry entry) {
        return new NewsEntry(
                entry.getTitle(),
                entry.getDescription(),
                entry.getLink(),
                entry.getPubDate());
    }
}
