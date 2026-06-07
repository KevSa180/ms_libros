package com.maovares.ms_books.book.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.GetAvailableBooksQuery;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class GetAvailableBooksService implements GetAvailableBooksQuery {

    private final BookRepository bookRepository;

    public GetAvailableBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> execute(String ownerId) {
        return bookRepository.findAvailableByOwner(ownerId);
    }
}
