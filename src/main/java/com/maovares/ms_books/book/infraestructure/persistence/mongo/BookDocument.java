package com.maovares.ms_books.book.infraestructure.persistence.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int loanDays;
    private String genre;
    private boolean available = true;
    private String borrowedBy;
    private List<ReviewDocument> reviews = new ArrayList<>();

    public BookDocument() {}

    public BookDocument(String id, String title, String author, String description, String image,
            int pageCount, String language, String uploadedBy, int loanDays, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.image = image;
        this.pageCount = pageCount;
        this.language = language;
        this.uploadedBy = uploadedBy;
        this.loanDays = loanDays;
        this.genre = genre;
        this.available = true;
        this.reviews = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public int getPageCount() { return pageCount; }
    public String getLanguage() { return language; }
    public String getUploadedBy() { return uploadedBy; }
    public int getLoanDays() { return loanDays; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return available; }
    public String getBorrowedBy() { return borrowedBy; }
    public List<ReviewDocument> getReviews() { return reviews; }
    public void setReviews(List<ReviewDocument> reviews) { this.reviews = reviews; }
}