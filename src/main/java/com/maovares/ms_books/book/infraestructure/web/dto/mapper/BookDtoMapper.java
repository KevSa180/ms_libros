package com.maovares.ms_books.book.infraestructure.web.dto.mapper;

import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.infraestructure.web.dto.BookResponseDto;

public class BookDtoMapper {
    public static BookResponseDto toResponse(Book book) {
        return BookResponseDto.fromDomain(book);
    }
}
