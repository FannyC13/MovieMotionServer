package com.example.moviemotionserver;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieInfo {
    private MovieSeance movie;
    private List<DateInfo> dates;

    @JsonProperty("titre")
    public MovieSeance getMovie() {
        return movie;
    }

    public void setMovie(MovieSeance movie) {
        this.movie = movie;
    }

    @JsonProperty("dates")
    public List<DateInfo> getDates() {
        return dates;
    }

    public void setDates(List<DateInfo> dates) {
        this.dates = dates;
    }
}
