package com.maovares.ms_books.book.application.port.in;

import com.maovares.ms_books.book.domain.model.Review;

public interface CreateReviewCommand {
    Review execute(String bookId, String author, String text, int stars);
}