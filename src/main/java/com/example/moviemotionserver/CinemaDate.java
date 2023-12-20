// CinemaDate.java
package com.example.moviemotionserver;

public class CinemaDate {
    private Cinema cinema;
    private Seance seance;

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}