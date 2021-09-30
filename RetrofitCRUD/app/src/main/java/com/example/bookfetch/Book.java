package com.example.bookfetch;
import com.google.gson.annotations.SerializedName;

public class Book {
    private String _id;

    private String title;

    private String content;

    private double rating;

    @SerializedName("isItAvailable")
    private boolean avail;

    public Book(String title, String content, double rating, boolean avail) {
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.avail = avail;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public double getRating() {
        return rating;
    }

    public boolean isAvail() {
        return avail;
    }
}
