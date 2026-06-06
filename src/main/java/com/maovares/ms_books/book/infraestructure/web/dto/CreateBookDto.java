package com.maovares.ms_books.book.infraestructure.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateBookDto(
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String description,
        @NotBlank String image,
        @Min(1) int pageCount,
        @NotBlank String language,
        @NotBlank String uploadedBy,
        int loanDays,
        String genre) {
}