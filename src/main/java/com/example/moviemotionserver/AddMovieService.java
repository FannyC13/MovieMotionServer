package com.example.moviemotionserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Path("/addMovie")
public class AddMovieService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_api", "root", "Tennis92!")) {
                MovieDAO movieDAO = new MovieDAO(connection);
                movieDAO.addMovie(movie);

                return Response.status(Response.Status.OK).entity("Film ajouté avec succès").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de l'ajout du film").build();
        }
    }
}
