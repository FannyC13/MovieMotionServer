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

@Path("/cinemas_all")
public class Cinema_All {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cinema> getCinemas() {
        List<Cinema> cinemas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM cinema")) {

            while (resultSet.next()) {
                Cinema cinema = new Cinema();
                cinema.setIdCinema(resultSet.getInt("Id_cinema"));
                cinema.setCinema(resultSet.getString("cinema"));
                cinema.setVille(resultSet.getString("ville"));

                cinemas.add(cinema);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cinemas;
    }
}