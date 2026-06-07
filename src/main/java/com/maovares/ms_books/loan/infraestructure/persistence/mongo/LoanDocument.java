package com.maovares.ms_books.loan.infraestructure.persistence.mongo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loans")
public class LoanDocument {
    @Id
    private String id;
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String lenderId;
    private String borrowerId;
    private int durationDays;
    private String conditionNotes;
    private String conditionPhotos;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime returnedAt;
    private String returnConditionNotes;
    private Integer rating;
    private String comment;

    public LoanDocument() {}

    public LoanDocument(String id, String bookId, String bookTitle, String bookAuthor,
            String lenderId, String borrowerId, int durationDays, String conditionNotes,
            String conditionPhotos, String status, LocalDateTime createdAt,
            LocalDateTime acceptedAt, LocalDateTime returnedAt, String returnConditionNotes,
            Integer rating, String comment) {
        this.id = id;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.lenderId = lenderId;
        this.borrowerId = borrowerId;
        this.durationDays = durationDays;
        this.conditionNotes = conditionNotes;
        this.conditionPhotos = conditionPhotos;
        this.status = status;
        this.createdAt = createdAt;
        this.acceptedAt = acceptedAt;
        this.returnedAt = returnedAt;
        this.returnConditionNotes = returnConditionNotes;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() { return id; }
    public String getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public String getBookAuthor() { return bookAuthor; }
    public String getLenderId() { return lenderId; }
    public String getBorrowerId() { return borrowerId; }
    public int getDurationDays() { return durationDays; }
    public String getConditionNotes() { return conditionNotes; }
    public String getConditionPhotos() { return conditionPhotos; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getAcceptedAt() { return acceptedAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public String getReturnConditionNotes() { return returnConditionNotes; }
    public Integer getRating() { return rating; }
    public String getComment() { return comment; }
}
