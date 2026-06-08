package com.maovares.ms_books.book.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.GetBooksQuery;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class GetBooksService implements GetBooksQuery {

    private final BookRepository bookRepository;

    public GetBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> execute(Pageable pageable, String search, String genre) {
        return bookRepository.findAll(pageable, search, genre);
    }
}
