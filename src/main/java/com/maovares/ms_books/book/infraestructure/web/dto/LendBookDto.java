package com.maovares.ms_books.book.infraestructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record LendBookDto(@NotBlank String borrowerId) {
}
