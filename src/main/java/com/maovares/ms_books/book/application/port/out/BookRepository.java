package com.maovares.ms_books.book.application.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.domain.model.Review;

public interface BookRepository {
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(String id);
    Book save(Book book);
    void deleteById(String id);

    // Reviews
    Review addReview(String bookId, Review review);
    List<Review> findReviewsByBookId(String bookId);
}