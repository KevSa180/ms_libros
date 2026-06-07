package com.maovares.ms_books.book.application.port.in;

import java.util.List;

import com.maovares.ms_books.book.domain.model.Book;

public interface GetAvailableBooksQuery {
    List<Book> execute(String ownerId);
}
