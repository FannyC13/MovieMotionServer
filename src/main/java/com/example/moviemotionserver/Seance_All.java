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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/seances_all")
public class Seance_All {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getSeancesWithMovies() {
        List<Map<String, Object>> seancesWithMovies = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM Seance JOIN Movie ON Seance.ID_movie = Movie.ID_movie JOIN Cinema on Cinema.Id_cinema = Seance.Id_cinema";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL");
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Map<String, Object> seanceMap = new HashMap<>();
                seanceMap.put("Id_seance", resultSet.getInt("Id_seance"));
                seanceMap.put("version", resultSet.getString("version"));
                seanceMap.put("heure", resultSet.getString("heure"));
                seanceMap.put("dateSeance", resultSet.getString("date_seance"));
                seanceMap.put("Id_cinema", resultSet.getInt("Id_cinema"));
                seanceMap.put("cinema", resultSet.getString("cinema"));
                seanceMap.put("Id_movie", resultSet.getInt("Id_movie"));
                seanceMap.put("titre", resultSet.getString("titre"));
                seanceMap.put("dateSortie", resultSet.getString("date_sortie"));

                seancesWithMovies.add(seanceMap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seancesWithMovies;
    }
}
