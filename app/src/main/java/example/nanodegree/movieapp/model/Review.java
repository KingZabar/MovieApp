package example.nanodegree.movieapp.model;

import java.io.Serializable;

/**
 * Created by oofong25 on 7/25/15.
 */
public class Review implements Serializable {

    public int id;
    public int page;
    public int total_pages;
    public int total_results;

    public ReviewFeed[] results;

    public Review(){}

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getSize() {
        if (results == null)
            return 0;

        return results.length;
    }

    public ReviewFeed[] getResults() {
        return results;
    }

    public static class ReviewFeed {
        public String id;
        public String author;
        public String content;
        public String url;

        public String getId() {
            return id;
        }

        public String getAuthor() {
            return author;
        }

        public String getContent() {
            return content;
        }

        public String getUrl() {
            return url;
        }
    }
}
