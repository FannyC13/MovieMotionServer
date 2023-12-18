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

@Path("/catalog-page-info/{movieId}")
public class Movie_By_ID_cinema_seance {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MovieInformation getMovieInformation(@PathParam("movieId") int movieId) {
        List<Cinema> cinemas = new ArrayList<>();
        List<Seance> seances = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_api", "root", "Tennis92!")) {
            String sql = "SELECT * FROM Seance JOIN Movie ON Seance.ID_movie = Movie.ID_movie" +
                    " JOIN Cinema ON Cinema.ID_cinema = Seance.ID_cinema WHERE Movie.ID_movie = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, movieId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Seance seance = new Seance();
                        seance.setIdSeance(resultSet.getInt("Id_seance"));
                        seance.setVersion(resultSet.getString("version"));
                        seance.setDateSeance(resultSet.getString("date_seance"));
                        seance.setIdCinema(resultSet.getInt("Id_cinema"));

                        Cinema cinema = new Cinema();
                        cinema.setIdCinema(resultSet.getInt("Id_cinema"));
                        cinema.setCinema(resultSet.getString("cinema"));
                        cinema.setVille(resultSet.getString("ville"));

                        cinemas.add(cinema);
                        seances.add(seance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MovieInformation movieInformation = new MovieInformation();
        movieInformation.setCinemas(cinemas);
        movieInformation.setSeances(seances);

        return movieInformation;
    }
}
