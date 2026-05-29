package com.maovares.ms_books.book.application.service;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.DeleteBookCommand;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.exception.BookNotFoundException;

@Service
public class DeleteBookService implements DeleteBookCommand {

    private final BookRepository bookRepository;

    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void execute(String id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book " + id + " not found"));
        bookRepository.deleteById(id);
    }
}
