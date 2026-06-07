package com.maovares.ms_books.book.application.service;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.ReturnBookCommand;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.exception.BookNotFoundException;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class ReturnBookService implements ReturnBookCommand {

    private final BookRepository bookRepository;

    public ReturnBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book execute(String bookId) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        return bookRepository.returnBook(bookId);
    }
}
