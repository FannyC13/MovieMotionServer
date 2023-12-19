package com.example.moviemotionserver;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/seances_all")
public class Seance_All {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Seance> getSeances() {
        List<Seance> seances = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM Seance JOIN Movie ON Seance.ID_movie = Movie.ID_movie";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Seance seance = new Seance();
                seance.setIdSeance(resultSet.getInt("Id_seance"));
                seance.setVersion(resultSet.getString("version"));
                seance.setDateSeance(resultSet.getString("date_seance"));
                seance.setIdCinema(resultSet.getInt("Id_cinema"));

                Movie movie = new Movie();
                movie.setIdMovie(resultSet.getInt("ID_movie"));
                movie.setTitre(resultSet.getString("titre"));
                movie.setDateSortie(resultSet.getString("date_sortie"));

                seances.add(seance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }
}