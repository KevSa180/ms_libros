package com.maovares.ms_books.book.infraestructure.web.dto;

import java.util.List;

public record PagedResponseDto<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages) {
}
