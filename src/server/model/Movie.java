package server.model;

import com.google.gson.internal.StringMap;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The class represents the movies
 * Created by andreea on 5/19/2017.
 */
public class Movie {
    private String poster;
    private String title;
    private String releaseYear;

    private String genres;
    private double ratingImbd;
    private double ratingSepFlix;
    private String overview;

    public Movie(String poster, String title, String releaseYEar) {
        this.poster = poster;
        this.title = title;
        this.releaseYear = releaseYEar;
        this.genres = "";
        this.ratingImbd = 0;
        this.ratingSepFlix = 0;
        this.overview = "";
    }

    public Movie(String poster, String title, String releaseYEar, String genres, double ratingImbd, double ratingSepFlix, String overview) {
        this.poster = poster;
        this.title = title;
        this.releaseYear = releaseYEar;
        this.genres = genres;
        this.ratingImbd = ratingImbd;
        this.ratingSepFlix = ratingSepFlix;
        this.overview = overview;
    }

    public Movie(StringMap<Object> data) {
        this.poster = (String) data.get("Poster");
        this.title = (String) data.get("Title");
        this.releaseYear = (String) data.get("ReleaseYear");
        if ((String) data.get("Genres") != null) {
            this.genres = (String) data.get("Genres");
            this.ratingImbd = Double.parseDouble((String) data.get("RatingImdb"));
            this.ratingSepFlix = Double.parseDouble((String) data.get("RatingSepFlix"));
            this.overview = (String) data.get("overview");
        }
    }

    public Movie(ResultSet resultSet) {
        try {
            this.poster = resultSet.getString("poster");
            this.title = resultSet.getString("title");
            this.releaseYear = resultSet.getString("release_year");
            if (resultSet.getString("Genres") != null) {
                this.genres = resultSet.getString("genres");
                this.ratingImbd = resultSet.getDouble("rating_Imbd");
                this.ratingSepFlix = resultSet.getDouble("reating_SepFlix");
                this.overview = resultSet.getString("overview");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getGenres() {
        return genres;
    }

    public double getRatingSepFlix() {
        return ratingSepFlix;
    }

    public double getRatingImbd() {
        return ratingImbd;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRatingImbd(double ratingImbd) {
        this.ratingImbd = ratingImbd;
    }

    public void setRatingSepFlix(double ratingSepFlix) {
        this.ratingSepFlix = ratingSepFlix;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String toStringSmall() {
        return this.poster+", title: "+this.title+", release year"+this.releaseYear;
    }
    public String toString() {
        return this.poster+", title: "+this.title+", release year: "+this.releaseYear +", genres: "+this.genres+", rating Imbd: "+this.ratingImbd+", rating Sep Flix: "+this.ratingSepFlix+", overview: "+this.overview;
    }
   }
