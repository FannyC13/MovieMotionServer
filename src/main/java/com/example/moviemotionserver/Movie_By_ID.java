package com.example.moviemotionserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/{movieId}")
public class Movie_By_ID {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies(@PathParam("movieId") int movieId) {
        List<Movie> movies = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_api", "root", "Tennis92!")) {
            String sql = "SELECT * FROM movie WHERE ID_movie = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, movieId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Movie movie = new Movie();
                        movie.setIdMovie(resultSet.getInt("ID_movie"));
                        movie.setTitre(resultSet.getString("titre"));
                        movie.setRealisateur(resultSet.getString("realisateur"));
                        movie.setDurationMin(resultSet.getInt("Duration"));
                        movie.setAge(resultSet.getInt("age"));
                        movie.setActeursPrincipaux(resultSet.getString("acteurs_principaux"));
                        movie.setSynopsis(resultSet.getString("synopsis"));
                        movie.setDateSortie(resultSet.getString("date_sortie"));
                        movie.setTrailer(resultSet.getString("trailer"));
                        movie.setLangue(resultSet.getString("langue"));
                        movie.setImageSrc(resultSet.getString("image_src"));
                        movie.setDateDebut(resultSet.getString("Date_debut"));
                        movie.setDateFin(resultSet.getString("Date_fin"));

                        movies.add(movie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
