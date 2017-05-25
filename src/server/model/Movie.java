package server.model;

import com.google.gson.internal.StringMap;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The class represents the movies
 * Created by andreea on 5/19/2017.
 */
public class Movie {
    private int id;
    private String poster;
    private String title;
    private String releaseYear;

    private String genres;
    private double ratingImbd;
    private double ratingSepFlix;
    private String overview;

    public Movie(int id, String poster, String title, String releaseYear, double ratingImbd) {
        this.id = id;
        this.poster = poster;
        this.title = title;
        this.releaseYear = releaseYear;
        this.genres = null;
        this.ratingImbd = ratingImbd;
        this.ratingSepFlix = 0;
        this.overview = null;
    }

    public Movie(int id, String poster, String title, String releaseYEar, String genres, double ratingImbd, double ratingSepFlix, String overview) {
        this.id = id;
        this.poster = poster;
        this.title = title;
        this.releaseYear = releaseYEar;
        this.genres = genres;
        this.ratingImbd = ratingImbd;
        this.ratingSepFlix = ratingSepFlix;
        this.overview = overview;
    }

    public Movie(StringMap<Object> data) {
        Double idDouble = (Double) data.get("id");
        this.id = idDouble.intValue();
        this.poster = (String) data.get("poster_path");
        this.title = (String) data.get("title");
        this.releaseYear = (String) data.get("release_date");
        this.ratingImbd = (Double) data.get("vote_average");
        if ((ArrayList<StringMap<Object>>) data.get("genres") != null) {
            for (StringMap<Object> genre : (ArrayList<StringMap<Object>>) data.get("genres")) {
                if (this.genres == null) {
                    this.genres = (String) genre.get("name");
                } else {
                    this.genres = this.genres + ", " + (String) genre.get("name");
                }
            }
            this.overview = (String) data.get("overview");
        }
    }

    public Movie(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id_movie");
            this.poster = resultSet.getString("poster");
            this.title = resultSet.getString("title");
            this.overview = resultSet.getString("overview");
            this.genres = resultSet.getString("genres");
            this.releaseYear = resultSet.getString("release_year");
            this.ratingImbd = resultSet.getDouble("rating_imdb");
            this.ratingSepFlix = resultSet.getDouble("rating_sepflix");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public StringMap<Object> toStringMap() {

        StringMap<Object> movie = new StringMap<>();
        movie.put("id", this.id);
        movie.put("poster_path", this.poster);
        movie.put("title", this.title);
        movie.put("release_date", this.releaseYear);
        movie.put("vote_average", this.ratingImbd);
        if (this.genres != null) {
            movie.put("genres", this.genres);
            movie.put("overview", this.overview);
            movie.put("vote_sepflix", this.ratingSepFlix);
        }
        return movie;
    }


    public String toStringSmall() {
        return this.poster + ", title: " + this.title + ", release year" + this.releaseYear;
    }

    public String toString() {
        return this.poster + ", title: " + this.title + ", release year: " + this.releaseYear + ", genres: " + this.genres + ", rating Imbd: " + this.ratingImbd + ", rating Sep Flix: " + this.ratingSepFlix + ", overview: " + this.overview;
    }

    public Date getSQLReleaseYear() {
        Date date = Date.valueOf(this.releaseYear);
        return date;
    }
}
