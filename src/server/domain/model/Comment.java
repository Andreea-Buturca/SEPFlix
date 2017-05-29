package server.domain.model;

import com.google.gson.internal.StringMap;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Stela on 26.5.2017.
 */
public class Comment {
    private String comment;
    private int idMovie;
    private String username;

    public Comment(String comment, int idMovie, String username) {
        this.comment = comment;
        this.idMovie = idMovie;
        this.username = username;
    }

    public Comment(StringMap<Object> data) {
        Double idDouble = (Double) data.get("id");
        this.idMovie = idDouble.intValue();
        this.username = (String) data.get("user_name");
        this.comment = (String) data.get("comment");
    }

    public Comment(ResultSet resultSet) {
        try {
            this.idMovie = resultSet.getInt("id_movie");
            this.comment = resultSet.getString("comment");
            this.username = resultSet.getString("user_name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public int getidMovie() {
        return idMovie;
    }

    public StringMap<Object> toStringMap() {
        StringMap<Object> comment = new StringMap<>();
        comment.put("user_name", this.username);
        comment.put("comment", this.comment);
        comment.put("id_Movie", this.idMovie);
        return comment;
    }
}
