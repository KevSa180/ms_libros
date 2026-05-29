package com.maovares.ms_books.book.application.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maovares.ms_books.book.domain.model.Book;

public interface BookRepository {
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(String id);

    Book save(Book book);

    void deleteById(String id);
}
