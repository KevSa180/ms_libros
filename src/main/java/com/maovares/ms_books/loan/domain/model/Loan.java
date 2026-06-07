package com.maovares.ms_books.loan.domain.model;

import java.time.LocalDateTime;

public class Loan {
    private final String id;
    private final String bookId;
    private final String bookTitle;
    private final String bookAuthor;
    private final String lenderId;     // quien presta (dueño)
    private final String borrowerId;   // a quien se le presta
    private final int durationDays;
    private final String conditionNotes;   // notas de condición al prestar
    private final String conditionPhotos;  // URLs/texto (front,back,spine,interior)
    private String status;             // pending | active | declined | returned
    private final LocalDateTime createdAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime returnedAt;
    private String returnConditionNotes;
    private Integer rating;            // estrellas al devolver
    private String comment;            // comentario sobre el usuario

    public Loan(String id, String bookId, String bookTitle, String bookAuthor,
            String lenderId, String borrowerId, int durationDays,
            String conditionNotes, String conditionPhotos, String status,
            LocalDateTime createdAt, LocalDateTime acceptedAt, LocalDateTime returnedAt,
            String returnConditionNotes, Integer rating, String comment) {
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
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getAcceptedAt() { return acceptedAt; }
    public void setAcceptedAt(LocalDateTime acceptedAt) { this.acceptedAt = acceptedAt; }
    public LocalDateTime getReturnedAt() { return returnedAt; }
    public void setReturnedAt(LocalDateTime returnedAt) { this.returnedAt = returnedAt; }
    public String getReturnConditionNotes() { return returnConditionNotes; }
    public void setReturnConditionNotes(String v) { this.returnConditionNotes = v; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
