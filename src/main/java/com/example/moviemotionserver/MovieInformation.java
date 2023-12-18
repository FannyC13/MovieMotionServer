package com.example.moviemotionserver;

import java.util.List;

public class MovieInformation {
    private List<Cinema> cinemas;
    private List<Seance> seances;

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
