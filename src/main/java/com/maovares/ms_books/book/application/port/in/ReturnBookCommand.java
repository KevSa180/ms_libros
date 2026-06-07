package com.maovares.ms_books.book.application.port.in;

import com.maovares.ms_books.book.domain.model.Book;

public interface ReturnBookCommand {
    Book execute(String bookId);
}
