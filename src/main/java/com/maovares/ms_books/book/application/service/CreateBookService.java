package com.maovares.ms_books.book.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.CreateBookCommand;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Book;

@Service
public class CreateBookService implements CreateBookCommand {

    private final BookRepository bookRepository;

    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book execute(String title, String author, String description, String image,
            int pageCount, String language, String uploadedBy) {
        String id = UUID.randomUUID().toString();
        Book book = new Book(id, title, author, description, image, pageCount, language, uploadedBy);
        return bookRepository.save(book);
    }
}
