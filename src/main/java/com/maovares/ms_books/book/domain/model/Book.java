package com.maovares.ms_books.book.domain.model;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private final String description;
    private final String image;
    private final int pageCount;
    private final String language;
    private final String uploadedBy;
    private final int loanDays;
    private final String genre;
    private final boolean available;
    private final String borrowedBy;

    public Book(String id, String title, String author, String description, String image,
            int pageCount, String language, String uploadedBy, int loanDays, String genre,
            boolean available, String borrowedBy) {
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
        this.available = available;
        this.borrowedBy = borrowedBy;
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
}