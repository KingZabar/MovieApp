package example.nanodegree.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by oofong25 on 7/18/15.
 */
public class Movie implements Parcelable {

    String title, releaseDate, plotSynopsis, posterImageUrl;
    double userRating, popularity;
    int movieId;

    public Movie() {
    }

    Movie(Parcel in) {
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.plotSynopsis = in.readString();
        this.posterImageUrl = in.readString();
        this.userRating = in.readDouble();
        this.popularity = in.readDouble();
        this.movieId = in.readInt();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(releaseDate);
        out.writeString(plotSynopsis);
        out.writeString(posterImageUrl);
        out.writeDouble(userRating);
        out.writeDouble(popularity);
        out.writeInt(movieId);
    }

    static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
