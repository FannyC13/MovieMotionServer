package com.example.moviemotionserver;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Path("/deleteMovie/{id}")
public class DeleteMovieService {

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") int movieId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviemotion", "root", "MySQL")) {
                MovieDAO movieDAO = new MovieDAO(connection);
                movieDAO.deleteMovie(movieId);

                return Response.status(Response.Status.OK).entity("Film supprimé avec succès").build();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la suppression du film").build();
        }
    }
}
