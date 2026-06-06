package com.maovares.ms_books.book.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.CreateReviewCommand;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Review;

@Service
public class CreateReviewService implements CreateReviewCommand {

    private final BookRepository bookRepository;

    public CreateReviewService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Review execute(String bookId, String author, String text, int stars) {
        String id = UUID.randomUUID().toString();
        String createdAt = Instant.now().toString();
        Review review = new Review(id, author, text, stars, createdAt);
        return bookRepository.addReview(bookId, review);
    }
}