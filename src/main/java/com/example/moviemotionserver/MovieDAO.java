package com.example.moviemotionserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDAO {
    private Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    public void addMovie(Movie movie) {
        try {
            String sql = "INSERT INTO Movie (titre, realisateur, duration, age, acteurs_principaux, synopsis, date_sortie, trailer, langue, image_src,genres) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, movie.getTitre());
                statement.setString(2, movie.getRealisateur());
                statement.setString(3, movie.getDurationMin());
                statement.setString(4, movie.getAge());
                statement.setString(5, movie.getActeursPrincipaux());
                statement.setString(6, movie.getSynopsis());
                statement.setString(7, movie.getDateSortie());
                statement.setString(8, movie.getTrailer());
                statement.setString(9, movie.getLangue());
                statement.setString(10, movie.getImageSrc());
                statement.setString(11, movie.getGenres());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteMovie(int movieId) {
        try {
            String sql = "DELETE FROM Movie WHERE ID_movie = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, movieId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateMovie(Movie movie) {
        try {
            String sql = "UPDATE Movie SET titre=?, realisateur=?, duration=?, age=?, acteurs_principaux=?, " +
                    "synopsis=?, date_sortie=?, trailer=?, langue=?, image_src=?, Date_debut=?, Date_fin=?, genres =? " +
                    "WHERE ID_movie=?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, movie.getTitre());
                statement.setString(2, movie.getRealisateur());
                statement.setString(3, movie.getDurationMin());
                statement.setString(4, movie.getAge());
                statement.setString(5, movie.getActeursPrincipaux());
                statement.setString(6, movie.getSynopsis());
                statement.setString(7, movie.getDateSortie());
                statement.setString(8, movie.getTrailer());
                statement.setString(9, movie.getLangue());
                statement.setString(10, movie.getImageSrc());
                statement.setString(11, movie.getDateDebut());
                statement.setString(12, movie.getDateFin());
                statement.setInt(13, movie.getIdMovie());
                statement.setString(14,movie.getGenres());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
