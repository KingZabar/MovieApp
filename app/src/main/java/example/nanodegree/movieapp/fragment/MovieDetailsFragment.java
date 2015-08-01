package example.nanodegree.movieapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import example.nanodegree.movieapp.Const;
import example.nanodegree.movieapp.Movie;
import example.nanodegree.movieapp.MovieDetailAdapter;
import example.nanodegree.movieapp.R;
import example.nanodegree.movieapp.Trailer;
import example.nanodegree.movieapp.Utils;


public class MovieDetailsFragment extends Fragment {
    View rootView;

    static final String TAG = MovieDetailsFragment.class.getSimpleName();
    static final String TRAILER_BASE_URL = "http://api.themoviedb.org/3/movie/MOVIE_ID/videos?&api_key=c0be370cd8ac272132f42a2c34b531d6";

    Movie movie;
    Trailer trailer = new Trailer();
    List<String> trailerNames = new ArrayList<>();
    ArrayAdapter<String> adapter;
    List dataSet = new ArrayList();
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public MovieDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_details_trailer, container, false);
        movie = getArguments().getParcelable(Const.KEY_MOVIE);
        setUpRecyclerView();
        new GetTrailerTask().execute();
        return rootView;
    }

    private void setUpRecyclerView() {

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        Log.d(TAG, "trailer --> " + trailer.getSize());

        // use a Grid layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<String> reviews = new ArrayList<>();
        reviews.add("review 1");
        reviews.add("review 2");
        reviews.add("review 3");
        // specify an adapter
        mAdapter = new MovieDetailAdapter(trailer, reviews, getActivity(), movie);
        mRecyclerView.setAdapter(mAdapter);


    }


    private class GetTrailerTask extends AsyncTask<Void, Void, Trailer> {

        @Override
        protected Trailer doInBackground(Void... params) {

            String content = Utils.getStringRequest(TRAILER_BASE_URL.replace("MOVIE_ID", movie.getMovieId() + ""));
            JSONObject jsonObject;
            if (content != null) {
                try {
                    jsonObject = new JSONObject(content);
                    Gson gson = new Gson();

                    trailer = gson.fromJson(jsonObject.toString(), Trailer.class);

                    return trailer;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Trailer trailer) {
            super.onPostExecute(trailer);
            setUpRecyclerView();
        }
    }
}
