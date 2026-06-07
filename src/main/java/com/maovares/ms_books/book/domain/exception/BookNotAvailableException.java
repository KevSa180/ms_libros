package com.maovares.ms_books.book.domain.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String bookId) {
        super("Book with id '" + bookId + "' is not available for lending.");
    }
}
