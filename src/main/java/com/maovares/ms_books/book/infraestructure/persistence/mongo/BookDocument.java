package com.maovares.ms_books.book.infraestructure.persistence.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "books")
public class BookDocument {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private String image;
    private int pageCount;
    private String language;
    private String uploadedBy;

    public BookDocument() {
    }

    public BookDocument(String id, String title, String author, String description, String image,
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
