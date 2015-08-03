package example.nanodegree.movieapp;


public class Const {

    public static final String MOVIE_DB_API_KEY = "c0be370cd8ac272132f42a2c34b531d6";
  //  public static final String MOVIE_DB_API_KEY = "YOUR_MOVIE_DB_API_KEY_HERE";


    public final static String KEY_MOVIE_LIST = "key_movie_list";
    public final static String KEY_MOVIE = "key_movie";
    public final static String PREF_FAV_MOVIE = "pref_fav_movie";


    public final static String TRAILER_BASE_URL = "http://api.themoviedb.org/3/movie/MOVIE_ID/videos?&api_key=" + MOVIE_DB_API_KEY;
    public final static String REVIEW_BASE_URL = "http://api.themoviedb.org/3/movie/MOVIE_ID/reviews?&api_key=" + MOVIE_DB_API_KEY;
    public final static String YOUTUBE_TRAILER_BASE_URL = "https://www.youtube.com/watch?v=";
}
