package example.nanodegree.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    String title, realeaseDate, plotSynopsis, imagePosterUrl;
    double userRating;
    TextView tvTitle, tvReleaseDate, tvPlotSynopsis, tvUserRating;
    ImageView imageViewPoster;
    ImageButton fav_button;
    Movie movie;
    // RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
        initToolBar();
        getMovieDetails();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.movie_details);
    }

    private void getMovieDetails() {
        movie = getIntent().getParcelableExtra(Const.KEY_MOVIE);
        if (movie != null) {
            title = movie.getTitle();
            realeaseDate = movie.getReleaseDate();
            plotSynopsis = movie.getPlotSynopsis();
            imagePosterUrl = movie.getPosterImageUrl();
            userRating = movie.getUserRating();

            initializeViews();
        }
    }

    private void initializeViews() {
        tvTitle = (TextView) findViewById(R.id.movie_details_title);
        tvReleaseDate = (TextView) findViewById(R.id.movie_details_release_date);
        tvPlotSynopsis = (TextView) findViewById(R.id.movie_details_plot_synopsis);
        tvUserRating = (TextView) findViewById(R.id.movie_details_rating);
        imageViewPoster = (ImageView) findViewById(R.id.movie_details_image_poster);
        fav_button = (ImageButton) findViewById(R.id.favorite_button);
        fav_button.setOnClickListener(this);
        //   ratingBar = (RatingBar) findViewById(R.id.movie_details_ratingBar);


        tvTitle.setText(title);
        tvReleaseDate.setText(getFormattedDate());
        tvPlotSynopsis.setText(plotSynopsis);
        tvUserRating.setText(userRating + "/" + getString(R.string.rating_total));
        // ratingBar.setRating((float) userRating);
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

    boolean theBoolean;

    private void toogleFavButton() {
        theBoolean ^= true;
        if (theBoolean)
            fav_button.setImageResource(android.R.drawable.btn_star_big_on);
        else fav_button.setImageResource(android.R.drawable.btn_star_big_off);


        if (movie != null) {
            Toast.makeText(this, "" + movie.getTitle(), Toast.LENGTH_SHORT).show();
        }
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
