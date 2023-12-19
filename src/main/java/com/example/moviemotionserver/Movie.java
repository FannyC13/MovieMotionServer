package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Movie {
    private int idMovie;
    private String titre;
    private String realisateur;
    private String durationMin;
    private String age;
    private String acteursPrincipaux;
    private String synopsis;
    private String dateSortie;
    private String trailer;
    private String langue;
    private String imageSrc;
    private String dateDebut;
    private String dateFin;

    public Movie() {
    }
    @JsonProperty("ID_movie")
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

    @JsonProperty("realisateur")
    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    @JsonProperty("duration")
    public String getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(String durationMin) {
        this.durationMin = durationMin;
    }

    @JsonProperty("age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JsonProperty("acteurs_principaux")
    public String getActeursPrincipaux() {
        return acteursPrincipaux;
    }

    public void setActeursPrincipaux(String acteursPrincipaux) {
        this.acteursPrincipaux = acteursPrincipaux;
    }

    @JsonProperty("synopsis")
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @JsonProperty("date_sortie")
    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    @JsonProperty("trailer")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @JsonProperty("langue")
    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    @JsonProperty("image_src")
    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @JsonProperty("Date_debut")
    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    @JsonProperty("Date_fin")
    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}