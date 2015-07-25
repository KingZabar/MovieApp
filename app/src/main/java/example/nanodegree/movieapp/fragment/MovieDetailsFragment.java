package example.nanodegree.movieapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.nanodegree.movieapp.Const;
import example.nanodegree.movieapp.Movie;
import example.nanodegree.movieapp.R;
import example.nanodegree.movieapp.Utils;


public class MovieDetailsFragment extends Fragment implements View.OnClickListener {
    View rootView;
 //   static final String TAG = MovieDetailsFragment.class.getSimpleName();
    String title, realeaseDate, plotSynopsis, imagePosterUrl;
    double userRating;
    TextView tvTitle, tvReleaseDate, tvPlotSynopsis, tvUserRating;
    ImageView imageViewPoster;
    Movie movie;
    ImageButton fav_button;

    public MovieDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_details, container, false);
        rootView.findViewById(R.id.toolbar).setVisibility(View.GONE);
        getMovieDetails();
        initializeViews();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavButton();
    }

    private void getMovieDetails() {
        movie = getArguments().getParcelable(Const.KEY_MOVIE);
        if (movie != null) {
            title = movie.getTitle();
            realeaseDate = movie.getReleaseDate();
            plotSynopsis = movie.getPlotSynopsis();
            imagePosterUrl = movie.getPosterImageUrl();
            userRating = movie.getUserRating();
        }
    }

    private void initializeViews() {
        tvTitle = (TextView) rootView.findViewById(R.id.movie_details_title);
        tvReleaseDate = (TextView) rootView.findViewById(R.id.movie_details_release_date);
        tvPlotSynopsis = (TextView) rootView.findViewById(R.id.movie_details_plot_synopsis);
        tvUserRating = (TextView) rootView.findViewById(R.id.movie_details_rating);
        imageViewPoster = (ImageView) rootView.findViewById(R.id.movie_details_image_poster);
        fav_button = (ImageButton) rootView.findViewById(R.id.favorite_button);
        fav_button.setOnClickListener(this);

        tvTitle.setText(title);
        tvReleaseDate.setText(getFormattedDate());
        tvPlotSynopsis.setText(plotSynopsis);
        tvUserRating.setText(userRating + "/" + getString(R.string.rating_total));
        Picasso.with(getActivity()).load(imagePosterUrl).into(imageViewPoster);
    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = sdf.parse(realeaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

        return sdf.format(date);
    }

    private void updateFavButton() {

        List<Movie> favoriteMovies = Utils.getFavoriteMovies(getActivity());
        boolean isFound = false;

        for (int i = 0; i < favoriteMovies.size(); i++)
            if (favoriteMovies.get(i).getMovieId() == movie.getMovieId())
                isFound = true;

        if (isFound)
            fav_button.setImageResource(android.R.drawable.btn_star_big_on);
        else fav_button.setImageResource(android.R.drawable.btn_star_big_off);
    }

    private void toogleFavButton() {
        Utils.updateFavoriteMovieList(getActivity(), movie);
        updateFavButton();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.favorite_button:
                toogleFavButton();
                break;
        }
    }
}
