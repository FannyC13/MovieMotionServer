package com.example.moviemotionserver;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(CorsFilter.class);
        resources.add(AddMovieService.class);
        resources.add(Cinema.class);
        resources.add(Cinema_All.class);
        resources.add(DeleteMovieService.class);
        resources.add(HelloResource.class);
        resources.add(Movie.class);
        resources.add(Movie_All.class);
        resources.add(Movie_By_ID.class);
        resources.add(Movie_By_ID_cinema_seance.class);
        resources.add(Movie_By_Title.class);
        resources.add(MovieDAO.class);
        resources.add(MovieInformation.class);
        resources.add(Seance.class);
        resources.add(Seance_All.class);
        resources.add(MovieInfo.class);
        resources.add(DateInfo.class);
        resources.add(CinemaInfo.class);
        resources.add(MovieCinemaSeance.class);
        return resources;
    }
}