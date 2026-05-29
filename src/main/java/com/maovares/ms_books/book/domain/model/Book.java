package com.maovares.ms_books.book.domain.model;

import lombok.Data;

@Data
public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final String description;
    private final String image;
    private final int pageCount;
    private final String language;
    private final String uploadedBy;

    public Book(String id, String title, String author, String description, String image,
            int pageCount, String language, String uploadedBy) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.pageCount = pageCount;
        this.language = language;
        this.uploadedBy = uploadedBy;
    }
}
