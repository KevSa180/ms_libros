package com.maovares.ms_books.book.application.port.out;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.domain.model.Review;

public interface BookRepository {
    Page<Book> findAll(Pageable pageable, String search, String genre);
    List<Book> findAvailableByOwner(String ownerId);
    List<Book> findBorrowedByUser(String userId);
    Optional<Book> findById(String id);
    Book save(Book book);
    void deleteById(String id);
    Book lend(String bookId, String borrowerId);
    Book returnBook(String bookId);

    // Reviews
    Review addReview(String bookId, Review review);
    List<Review> findReviewsByBookId(String bookId);
}