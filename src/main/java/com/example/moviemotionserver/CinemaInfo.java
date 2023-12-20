package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CinemaInfo {
    private Cinema cinema;
    private List<Seance> seances;

    @JsonProperty("name")
    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @JsonProperty("seances")
    public List<Seance> getSeances() {
        return seances;
    }

    public void setSeances(List<Seance> seances) {
        this.seances = seances;
    }
}
