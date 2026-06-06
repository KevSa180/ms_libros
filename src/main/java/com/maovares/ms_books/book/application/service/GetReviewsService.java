package com.maovares.ms_books.book.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.book.application.port.in.GetReviewsQuery;
import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Review;

@Service
public class GetReviewsService implements GetReviewsQuery {

    private final BookRepository bookRepository;

    public GetReviewsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> execute(String bookId) {
        return bookRepository.findReviewsByBookId(bookId);
    }
}