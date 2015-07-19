package example.nanodegree.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MovieDetailsActivity extends AppCompatActivity {

    String title, realeaseDate, plotSynopsis, imagePosterUrl;
    double userRating;
    TextView tvTitle, tvReleaseDate, tvPlotSynopsis, tvUserRating;
    ImageView imageViewPoster;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        initToolBar();
        getMovieDetails();
        initializeViews();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movie_details);
    }

    private void getMovieDetails() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(Const.KEY_TITLE);
            realeaseDate = bundle.getString(Const.KEY_RELEASE_DATE);
            plotSynopsis = bundle.getString(Const.KEY_PLOT_SYNOPSIS);
            imagePosterUrl = bundle.getString(Const.KEY_IMAGE_URL).replace("/w185/", "/w500/");
            userRating = bundle.getDouble(Const.KEY_USER_RATING);
        }
    }

    private void initializeViews() {
        tvTitle = (TextView) findViewById(R.id.movie_details_title);
        tvReleaseDate = (TextView) findViewById(R.id.movie_details_release_date);
        tvPlotSynopsis = (TextView) findViewById(R.id.movie_details_plot_synopsis);
        tvUserRating = (TextView) findViewById(R.id.movie_details_rating);
        imageViewPoster = (ImageView) findViewById(R.id.movie_details_image_poster);
        ratingBar = (RatingBar) findViewById(R.id.movie_details_ratingBar);


        tvTitle.setText(title);
        tvReleaseDate.setText(getFormattedDate());
        tvPlotSynopsis.setText(plotSynopsis);
        tvUserRating.setText("" + userRating);
        ratingBar.setRating((float) userRating);
        Picasso.with(this).load(imagePosterUrl).into(imageViewPoster);
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
}
