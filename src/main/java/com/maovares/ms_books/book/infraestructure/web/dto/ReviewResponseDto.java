package com.maovares.ms_books.book.infraestructure.web.dto;

import com.maovares.ms_books.book.domain.model.Review;

public record ReviewResponseDto(
        String id,
        String author,
        String text,
        int stars,
        String createdAt) {

    public static ReviewResponseDto fromDomain(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getAuthor(),
                review.getText(),
                review.getStars(),
                review.getCreatedAt());
    }
}