package com.maovares.ms_books.book.infraestructure.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateReviewDto(
        @NotBlank String author,
        @NotBlank String text,
        @Min(1) @Max(5) int stars) {
}