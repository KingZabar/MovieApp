package example.nanodegree.movieapp;

import java.io.Serializable;

/**
 * Created by oofong25 on 7/25/15.
 */
public class Review implements Serializable {

    public int id;

    public TrailerFeed[] results;

    public int getId(){
        return id;
    }

    public int getSize() {
        return results.length;
    }

    public TrailerFeed[] getResults(){
        return results;
    }

    public static class TrailerFeed {
        public String key;
        public String name;
        public String site;

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        public String getSite() {
            return site;
        }
    }
}
