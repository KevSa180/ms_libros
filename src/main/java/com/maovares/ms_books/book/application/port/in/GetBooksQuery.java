package com.maovares.ms_books.book.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maovares.ms_books.book.domain.model.Book;

public interface GetBooksQuery {
    Page<Book> execute(Pageable pageable, String search, String genre);
}
