package server.domain.model;

import com.google.gson.internal.StringMap;
import org.junit.Before;
import org.junit.Test;
import server.Main;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by andreea on 5/28/2017.
 */
public class MovieTest {
    private Movie movie;
    private ResultSet resultSet;
    private StringMap<Object> data = new StringMap<>();

    @Before
    public void setUp() throws Exception {

        //1
        //   this.movie = DatabaseConnection.getDatabaseConnection().getMovieById(335797);
        // ..
//        this.movie=Main.connectionREST.getMovie(335797);
        //..
        //      this.movie = new Movie(data);
        //or
//            this.movie= new Movie(resultSet);
        //or

      /*  Connection connection = null;
        DatabaseConnection.getDatabaseConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO movies " +
                            "(id_movie, poster, title, genres, overview, release_year, rating_imdb)" +
                            "VALUES (?,?,?,?,?,?,?);"
            );
            statement.setInt(1, movie.getId());
            statement.setString(2, movie.getPoster());
            statement.setString(3, movie.getTitle());
            statement.setString(4, movie.getGenres());
            statement.setString(5, movie.getOverview());
            statement.setDate(6, movie.getSQLReleaseYear());
            statement.setDouble(7, movie.getRatingImbd());
            statement.execute();
            statement.close();

            resultSet = statement.executeQuery();
            this.movie = new Movie(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void getId() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(335797);
        assertEquals(335797, this.movie.getId());
        assertNotEquals(111, this.movie.getId());
    }
/*
    @Test
    public void getTitle() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals("Beauty and the Beast", this.movie.getTitle());
        assertNotEquals("BEAUTY AND THE BEAST", this.movie.getTitle());
    }

    @Test
    public void getPoster() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals("/tWqifoYuwLETmmasnGHO7xBjEtt.jpg", this.movie.getPoster());
        assertNotEquals(111, this.movie.getPoster());
    }

    @Test
    public void getOverview() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals("A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.", this.movie.getOverview());
        assertNotEquals(null, this.movie.getOverview());
    }

    @Test
    public void getGenres() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals("Music, Family, Fantasy, Romance", this.movie.getGenres());
        assertNotEquals("Music", this.movie.getOverview());
    }

    @Test
    public void setComments() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        Comment c1 = new Comment("good", 321612, "a");
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(c1);
        this.movie.setComments(comments);
        assertEquals("good", this.movie.getComments());
    }

    @Test
    public void getRatingImbd() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals(6.8, 6.8, this.movie.getRatingImbd());
        assertNotEquals(10.00, 6.8, this.movie.getRatingImbd());
    }

    @Test
    public void toStringMap() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals("\"id\":321612,\"poster_path\":\"/tWqifoYuwLETmmasnGHO7xBjEtt.jpg\",\"title\":\"Beauty and the Beast\",\"release_date\":\"2017-03-16\",\"vote_average\":6.8,\"genres\":\"Music, Family, Fantasy, Romance\",\"overview\":\"A live-action adaptation of Disney\\u0027s version of the classic \\u0027Beauty and the Beast\\u0027 tale of a cursed prince and a beautiful young woman who helps him break the spell.\",\"vote_sepflix\":0.0,\"comments\":[]", this.movie.toStringMap());
    }

    @Test
    public void getSQLReleaseYear() throws Exception {
        this.movie = Main.databaseConnection.getMovieById(321612);
        assertEquals(2017 - 03 - 16, this.movie.getSQLReleaseYear());
        assertNotEquals("2017-03-16", this.movie.getSQLReleaseYear());
    }*/
}