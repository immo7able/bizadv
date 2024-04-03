package org.example.entity;

public class Anime {
    private int anime_id;
    private String name;
    private int seriesCount;
    private String status;
    private String studio;
    private String typeOfAnime;
    private String sourceOfAnime;
    private String avatar;
    private String background;
    private double rating;
    private int views;
    private int date;

    public int getId() {
        return anime_id;
    }

    public void setId(int id) {
        this.anime_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeriesCount() {
        return seriesCount;
    }

    public void setSeriesCount(int seriesCount) {
        this.seriesCount = seriesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getTypeOfAnime() {
        return typeOfAnime;
    }

    public void setTypeOfAnime(String typeOfAnime) {
        this.typeOfAnime = typeOfAnime;
    }

    public String getSourceOfAnime() {
        return sourceOfAnime;
    }

    public void setSourceOfAnime(String sourceOfAnime) {
        this.sourceOfAnime = sourceOfAnime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
