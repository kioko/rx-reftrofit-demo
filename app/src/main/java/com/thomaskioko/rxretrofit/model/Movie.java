package com.thomaskioko.rxretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Movie Object Class
 *
 * @author Kioko
 * @version Version
 */


public class Movie {

    private List<String> genre = new ArrayList<>();
    @SerializedName(value = "poster_path")
    public String posterUrl;
    private Double rating;
    private Integer releaseYear;
    private String title;

    /**
     * @return The genre
     */
    public List<String> getGenre() {
        return genre;
    }

    /**
     * @param genre The genre
     */
    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    /**
     * @return The posterUrl
     */
    public String getPosterUrl() {
        return posterUrl;
    }

    /**
     * @param posterUrl The posterUrl
     */
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    /**
     * @return The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * @return The releaseYear
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear The releaseYear
     */
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }


}
