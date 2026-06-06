package com.maovares.ms_books.book.application.port.in;

import java.util.List;

import com.maovares.ms_books.book.domain.model.Review;

public interface GetReviewsQuery {
    List<Review> execute(String bookId);
}