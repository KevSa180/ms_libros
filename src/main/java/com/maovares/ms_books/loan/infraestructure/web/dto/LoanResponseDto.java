package com.maovares.ms_books.loan.infraestructure.web.dto;

import java.time.LocalDateTime;

import com.maovares.ms_books.loan.domain.model.Loan;

public record LoanResponseDto(
        String id,
        String bookId,
        String bookTitle,
        String bookAuthor,
        String lenderId,
        String borrowerId,
        int durationDays,
        String conditionNotes,
        String conditionPhotos,
        String status,
        LocalDateTime createdAt,
        LocalDateTime acceptedAt,
        LocalDateTime returnedAt,
        String returnConditionNotes,
        Integer rating,
        String comment) {

    public static LoanResponseDto fromDomain(Loan l) {
        return new LoanResponseDto(
                l.getId(), l.getBookId(), l.getBookTitle(), l.getBookAuthor(),
                l.getLenderId(), l.getBorrowerId(), l.getDurationDays(),
                l.getConditionNotes(), l.getConditionPhotos(), l.getStatus(),
                l.getCreatedAt(), l.getAcceptedAt(), l.getReturnedAt(),
                l.getReturnConditionNotes(), l.getRating(), l.getComment());
    }
}
