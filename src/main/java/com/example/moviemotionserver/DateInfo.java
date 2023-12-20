package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import  java.sql.Date ;
import java.util.List;

public class DateInfo {
    private java.util.Date date;
    private String jour;
    private int jourChiffre;
    private String mois;
    private List<CinemaInfo> cinemas;



    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public int getJourChiffre() {
        return jourChiffre;
    }

    public void setJourChiffre(int jourChiffre) {
        this.jourChiffre = jourChiffre;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public List<CinemaInfo> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<CinemaInfo> cinemas) {
        this.cinemas = cinemas;
    }
}

