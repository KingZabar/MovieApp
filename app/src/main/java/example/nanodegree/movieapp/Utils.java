package example.nanodegree.movieapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import example.nanodegree.movieapp.model.Movie;


public class Utils {

    // private static String TAG = Utils.class.getSimpleName();

    /**
     * Returns String content from a url.
     */
    public static String getStringRequest(String url) {

        try {
            InputStream inputStream = new URL(url).openConnection().getInputStream();

            if (inputStream == null)
                return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            inputStream.close();

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static void updateFavoriteMovieList(Context context, Movie movie) {
        if (movie != null) {
            List<Movie> favoriteMovies = getFavoriteMovies(context);
            boolean isFound = false;

            for (int i = 0; i < favoriteMovies.size(); i++)
                if (favoriteMovies.get(i).getMovieId() == movie.getMovieId()) {
                    isFound = true;
                    if (isFound) {
                        favoriteMovies.remove(favoriteMovies.get(i));

                        Gson gson = new Gson();
                        String json = gson.toJson(favoriteMovies);
                        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Const.PREF_FAV_MOVIE, json).commit();
                    }
                }
            if (!isFound) {
                favoriteMovies.add(movie);

                Gson gson = new Gson();
                String json = gson.toJson(favoriteMovies);
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Const.PREF_FAV_MOVIE, json).commit();

            }

        }

    }

    public static void saveFavoriteMovie(Context context, Movie movie) {
        if (movie != null) {
            List<Movie> favoriteMovies = getFavoriteMovies(context);
            boolean isFound = false;

            for (int i = 0; i < favoriteMovies.size(); i++)
                if (favoriteMovies.get(i).getMovieId() == movie.getMovieId())
                    isFound = true;


            if (!isFound) {
                favoriteMovies.add(movie);
                Gson gson = new Gson();
                String json = gson.toJson(favoriteMovies);
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Const.PREF_FAV_MOVIE, json).commit();

            }

        }

    }

    public static void removeFavoriteMovie(Context context, Movie movie) {
        if (movie != null) {
            List<Movie> favoriteMovies = getFavoriteMovies(context);
            boolean isFound = false;

            for (int i = 0; i < favoriteMovies.size(); i++)
                if (favoriteMovies.get(i).getMovieId() == movie.getMovieId()) {
                    isFound = true;
                    if (isFound) {
                        favoriteMovies.remove(favoriteMovies.get(i));
                        Gson gson = new Gson();
                        String json = gson.toJson(favoriteMovies);
                        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(Const.PREF_FAV_MOVIE, json).commit();
                    }
                }

        }

    }

    public static List<Movie> getFavoriteMovies(Context context) {

        String json = PreferenceManager.getDefaultSharedPreferences(context).getString(Const.PREF_FAV_MOVIE, null);

        if (json != null) {
            Gson gson = new Gson();
            Type typeToken = new TypeToken<List<Movie>>() {
            }.getType();

            // List<Movie> favoriteMovies = gson.fromJson(json, typeToken);
            return gson.fromJson(json, typeToken);
        }

        return new ArrayList<>();
    }
}
