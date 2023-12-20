package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSeance {
    private int idMovie;
    private String titre;

    public MovieSeance() {
    }
    @JsonProperty("Id_movie")
    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    @JsonProperty("titre")
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


}