package com.maovares.ms_books.loan.infraestructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateLoanDto(
        @NotBlank String bookId,
        @NotBlank String bookTitle,
        @NotBlank String bookAuthor,
        @NotBlank String lenderId,
        @NotBlank String borrowerId,
        int durationDays,
        String conditionNotes,
        String conditionPhotos) {
}
