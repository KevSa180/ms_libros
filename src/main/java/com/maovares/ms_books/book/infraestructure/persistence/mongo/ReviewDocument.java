package com.maovares.ms_books.book.infraestructure.persistence.mongo;

public class ReviewDocument {
    private String id;
    private String author;
    private String text;
    private int stars;
    private String createdAt;

    public ReviewDocument() {}

    public ReviewDocument(String id, String author, String text, int stars, String createdAt) {
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
    public void setId(String id) { this.id = id; }
    public void setAuthor(String author) { this.author = author; }
    public void setText(String text) { this.text = text; }
    public void setStars(int stars) { this.stars = stars; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}