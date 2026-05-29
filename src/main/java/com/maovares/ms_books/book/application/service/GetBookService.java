package com.maovares.ms_books.book.application.service;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.GetBookQuery;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.exception.BookNotFoundException;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class GetBookService implements GetBookQuery {

    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book execute(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book " + id + " not found"));
    }
}
