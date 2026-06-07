package com.maovares.ms_books.loan.infraestructure.web.dto;

public record ReturnLoanDto(
        String returnConditionNotes,
        Integer rating,
        String comment) {
}
