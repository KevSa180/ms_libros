package com.maovares.ms_books.book.domain.model;

import lombok.Data;

@Data
public class Review {
    private final String id;
    private final String author;
    private final String text;
    private final int stars;
    private final String createdAt;
}