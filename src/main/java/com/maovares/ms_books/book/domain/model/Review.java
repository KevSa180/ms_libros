package com.maovares.ms_books.book.domain.model;

public class Review {
    private final String id;
    private final String author;
    private final String text;
    private final int stars;
    private final String createdAt;

    public Review(String id, String author, String text, int stars, String createdAt) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.stars = stars;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public String getAuthor() { return author; }
    public String getText() { return text; }
    public int getStars() { return stars; }
    public String getCreatedAt() { return createdAt; }
}