package com.example.moviemotionserver;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;

@Path("/movies-cinemas-seances")
public class MovieCinemaSeance{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieInfo> getAllMoviesInfo() {
        List<MovieInfo> moviesInfo = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL")) {
            String sql = "SELECT * FROM Movie";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        MovieInfo movieInfo = new MovieInfo();
                        MovieSeance movie = new MovieSeance();
                        movie.setIdMovie(resultSet.getInt("Id_movie"));
                        movie.setTitre(resultSet.getString("titre"));

                        movieInfo.setMovie(movie);
                        movieInfo.setDates(getMovieDatesInfo(connection, movie.getIdMovie()));

                        moviesInfo.add(movieInfo);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moviesInfo;
    }

    private List<DateInfo> getMovieDatesInfo(Connection connection, int movieId) throws SQLException {
        List<DateInfo> datesInfo = new ArrayList<>();
        String sql = "SELECT DISTINCT date_seance FROM Seance WHERE Id_movie = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DateInfo dateInfo = new DateInfo();
                    java.sql.Date sqlDate = resultSet.getDate("date_seance");

                    // Convert java.sql.Date to java.util.Date
                    java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

                    // Set the utilDate to the DateInfo object
                    dateInfo.setDate(utilDate);

                    // Extract day, day number, and month
                    dateInfo.setJour(new SimpleDateFormat("E").format(utilDate)); // Day of the week
                    dateInfo.setJourChiffre(Integer.parseInt(new SimpleDateFormat("d").format(utilDate))); // Day of the month
                    dateInfo.setMois(new SimpleDateFormat("MMM").format(utilDate).toLowerCase()); // Month abbreviation

                    dateInfo.setCinemas(getDateCinemasInfo(connection, movieId, dateInfo.getDate()));

                    datesInfo.add(dateInfo);
                }
            }
        }
        return datesInfo;
    }

    private List<CinemaInfo> getDateCinemasInfo(Connection connection, int movieId, String date) throws SQLException {
        List<CinemaInfo> cinemasInfo = new ArrayList<>();
        String sql = "SELECT DISTINCT Id_cinema FROM Seance WHERE Id_movie = ? AND date_seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieId);
            statement.setString(2, date);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CinemaInfo cinemaInfo = new CinemaInfo();
                    cinemaInfo.setCinema(getCinemaInfo(connection, resultSet.getInt("Id_cinema")));
                    cinemaInfo.setSeances(getCinemaSeancesInfo(connection, movieId, resultSet.getInt("Id_cinema"), date));

                    cinemasInfo.add(cinemaInfo);
                }
            }
        }
        return cinemasInfo;
    }

    private Cinema getCinemaInfo(Connection connection, int cinemaId) throws SQLException {
        Cinema cinema = new Cinema();
        String sql = "SELECT * FROM Cinema WHERE Id_cinema = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cinemaId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cinema.setIdCinema(resultSet.getInt("Id_cinema"));
                    cinema.setCinema(resultSet.getString("cinema"));
                    cinema.setVille(resultSet.getString("ville"));
                }
            }
        }
        return cinema;
    }

    private List<Seance> getCinemaSeancesInfo(Connection connection, int movieId, int cinemaId, String date) throws SQLException {
        List<Seance> seancesInfo = new ArrayList<>();
        String sql = "SELECT * FROM Seance WHERE Id_movie = ? AND Id_cinema = ? AND date_seance = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movieId);
            statement.setInt(2, cinemaId);
            statement.setString(3, date);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Seance seance = new Seance();
                    seance.setIdSeance(resultSet.getInt("Id_seance"));
                    seance.setHeure(resultSet.getString("heure"));
                    seance.setVersion(resultSet.getString("version"));
                    seance.setDateSeance(resultSet.getString("date_seance"));
                    seance.setIdCinema(resultSet.getInt("Id_cinema"));
                    seance.setIdMovie(resultSet.getInt("Id_movie"));

                    seancesInfo.add(seance);
                }
            }
        }
        return seancesInfo;
    }
}
