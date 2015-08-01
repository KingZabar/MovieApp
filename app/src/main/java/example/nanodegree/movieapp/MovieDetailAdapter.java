package example.nanodegree.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by oofong25 on 7/31/15.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.ViewHolder> {

    List mDataSet = new ArrayList();
    Trailer trailer = new Trailer();
    List<String> reviews = new ArrayList();
    Movie movie;
    Context context;
    static final int TYPE_MOVIE_DETAILS = R.layout.movie_details;
    static final int TYPE_HEADER = R.layout.item_textview;
    static final int TYPE_TRAILER = R.layout.item_trailer;
    static final int TYPE_REVIEW = R.layout.item_review;


    public MovieDetailAdapter(Trailer trailer, List<String> reviews, Context context, Movie movie) {
        this.movie = movie;
        this.context = context;
        this.trailer = trailer;
        this.reviews = reviews;

        mDataSet.add(context.getString(R.string.trailers));

        if (trailer != null)
            for (int i = 0; i < trailer.getSize(); i++) {
                mDataSet.add(trailer.getResults()[i]);
            }

        mDataSet.add(context.getString(R.string.reviews));
        mDataSet.addAll(reviews);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTrailerTextView;
        public TextView mReviewTextView;
        public TextView mHeaderTextView;

        //Movie Details View

        public TextView tvTitle, tvReleaseDate, tvPlotSynopsis, tvUserRating;
        public ImageView imageViewPoster;
        public ImageButton fav_button;

        public ViewHolder(View rootItemView) {
            super(rootItemView);
            // set view by Ids here
            //Movie details

            tvTitle = (TextView) rootItemView.findViewById(R.id.movie_details_title);
            tvReleaseDate = (TextView) rootItemView.findViewById(R.id.movie_details_release_date);
            tvPlotSynopsis = (TextView) rootItemView.findViewById(R.id.movie_details_plot_synopsis);
            tvUserRating = (TextView) rootItemView.findViewById(R.id.movie_details_rating);
            imageViewPoster = (ImageView) rootItemView.findViewById(R.id.movie_details_image_poster);
            fav_button = (ImageButton) rootItemView.findViewById(R.id.favorite_button);

            // Others
            mTrailerTextView = (TextView) rootItemView.findViewById(R.id.textView_trailer);
            mReviewTextView = (TextView) rootItemView.findViewById(R.id.textView_review);
            mHeaderTextView = (TextView) rootItemView.findViewById(R.id.textView_item);

            //
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // rv contains extra item row.
        int pos = position - 1;

        switch (holder.getItemViewType()) {

            case TYPE_MOVIE_DETAILS:
                if (holder.fav_button != null)
                    updateFavButton(holder.fav_button);
                    holder.fav_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toogleFavButton(holder.fav_button);

                        }
                    });

                holder.tvTitle.setText(movie.getTitle());
                holder.tvReleaseDate.setText(getFormattedDate());
                holder.tvPlotSynopsis.setText(movie.getPlotSynopsis());
                holder.tvUserRating.setText(movie.getUserRating() + "/" + context.getString(R.string.rating_total));
                Picasso.with(context).load(movie.getPosterImageUrl()).into(holder.imageViewPoster);

                break;

            case TYPE_TRAILER:
                final String name = ((Trailer.TrailerFeed) mDataSet.get(pos)).getName();
                final String key = ((Trailer.TrailerFeed) mDataSet.get(pos)).getKey();
                holder.mTrailerTextView.setText(name);
                holder.mTrailerTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case TYPE_REVIEW:
                final String review = (String) mDataSet.get(pos);
                holder.mReviewTextView.setText(review);
                holder.mReviewTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, review, Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case TYPE_HEADER:
                final String heading = (String) mDataSet.get(pos);
                holder.mHeaderTextView.setText(heading);
                break;
        }
    }

    @Override
    public int getItemCount() {
        // TYPE_MOVIE_DETAILS row is the extra row
        return mDataSet.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if ((position) == 0)
            return TYPE_MOVIE_DETAILS;
        else if (position == 1)
            return TYPE_HEADER;
        else if (trailer.getSize() > 0 && position <= trailer.getSize() + 1)
            return TYPE_TRAILER;
        else if (position == trailer.getSize() + 2)
            return TYPE_HEADER;
        else return TYPE_REVIEW;

        //  return super.getItemViewType(position);
    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = sdf.parse(movie.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);

        return sdf.format(date);
    }


    private void updateFavButton(ImageButton imageButton) {

        List<Movie> favoriteMovies = Utils.getFavoriteMovies(context);
        boolean isFound = false;

        for (int i = 0; i < favoriteMovies.size(); i++)
            if (favoriteMovies.get(i).getMovieId() == movie.getMovieId())
                isFound = true;

        if (isFound && imageButton != null)
            imageButton.setImageResource(android.R.drawable.btn_star_big_on);
        else imageButton.setImageResource(android.R.drawable.btn_star_big_off);
    }

    private void toogleFavButton(ImageButton imageButton) {
        Utils.updateFavoriteMovieList(context, movie);
        updateFavButton(imageButton);
    }

}
