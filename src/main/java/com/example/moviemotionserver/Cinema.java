package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cinema {
    private int idCinema;
    private String cinema;
    private String ville;

    @JsonProperty("Id_cinema")
    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    @JsonProperty("cinema")
    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    @JsonProperty("ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
