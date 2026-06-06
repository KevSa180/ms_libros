package com.maovares.ms_books.book.infraestructure.persistence.mongo;

import lombok.Data;

@Data
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
}