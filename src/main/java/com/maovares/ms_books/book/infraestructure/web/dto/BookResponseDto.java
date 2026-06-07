package com.maovares.ms_books.book.infraestructure.web.dto;

import com.maovares.ms_books.book.domain.model.Book;

public record BookResponseDto(
        String id,
        String title,
        String author,
        String description,
        String image,
        int pageCount,
        String language,
        String uploadedBy,
        int loanDays,
        String genre,
        boolean available,
        String borrowedBy) {

    public static BookResponseDto fromDomain(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getImage(),
                book.getPageCount(),
                book.getLanguage(),
                book.getUploadedBy(),
                book.getLoanDays(),
                book.getGenre(),
                book.isAvailable(),
                book.getBorrowedBy());
    }
}