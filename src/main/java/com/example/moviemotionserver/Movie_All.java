package com.example.moviemotionserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Path("/movies_all")
public class Movie_All {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM movie")) {

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setIdMovie(resultSet.getInt("ID_movie"));
                movie.setTitre(resultSet.getString("titre"));
                movie.setRealisateur(resultSet.getString("realisateur"));
                movie.setDurationMin(resultSet.getString("duration"));
                movie.setAge(resultSet.getString("age"));
                movie.setActeursPrincipaux(resultSet.getString("acteurs_principaux"));
                movie.setSynopsis(resultSet.getString("synopsis"));
                movie.setDateSortie(resultSet.getString("date_sortie"));
                movie.setTrailer(resultSet.getString("trailer"));
                movie.setLangue(resultSet.getString("langue"));
                movie.setImageSrc(resultSet.getString("image_src"));
                movie.setGenres(resultSet.getString("genres"));
                movie.setDateDebut(resultSet.getString("Date_debut"));
                movie.setDateFin(resultSet.getString("Date_fin"));

                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}