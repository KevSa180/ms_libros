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
            int pageCount, String language, String uploadedBy, int loanDays, String genre) {
        String id = UUID.randomUUID().toString();
        int days = loanDays > 0 ? loanDays : 14;
        String bookGenre = (genre != null && !genre.isBlank()) ? genre : "General";
        Book book = new Book(id, title, author, description, image, pageCount, language,
                uploadedBy, days, bookGenre);
        return bookRepository.save(book);
    }
}