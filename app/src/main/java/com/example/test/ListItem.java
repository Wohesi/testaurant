package com.example.test;

public class ListItem {

    private String title;
    private String overview;
    private int id;
    private int vote_average;
    private String poster_path;
    private String release_date;

    public ListItem(String title, String overview,  String release_date, String poster_path, int id, int vote_average) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.id = id;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public int getVote_average() {
        return vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
