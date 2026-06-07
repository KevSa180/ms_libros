package com.maovares.ms_books.book.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.GetBorrowedBooksQuery;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class GetBorrowedBooksService implements GetBorrowedBooksQuery {

    private final BookRepository bookRepository;

    public GetBorrowedBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> execute(String userId) {
        return bookRepository.findBorrowedByUser(userId);
    }
}
