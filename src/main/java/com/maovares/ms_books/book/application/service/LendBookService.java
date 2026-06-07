package com.maovares.ms_books.book.application.service;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.LendBookCommand;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.exception.BookNotAvailableException;
import com.maovares.ms_books.book.domain.exception.BookNotFoundException;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class LendBookService implements LendBookCommand {

    private final BookRepository bookRepository;

    public LendBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book execute(String bookId, String borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(bookId);
        }

        return bookRepository.lend(bookId, borrowerId);
    }
}
