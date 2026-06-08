package com.maovares.ms_books.book.infraestructure.persistence.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.maovares.ms_books.book.application.port.out.BookRepository;
import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.domain.model.Review;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BookMongoAdapter implements BookRepository {

    private final MongoTemplate mongoTemplate;

    public BookMongoAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<Book> findAll(Pageable pageable, String search, String genre) {
        long pageSize = pageable.getPageSize();
        long pageNumber = pageable.getPageNumber();
        long skip = pageNumber * pageSize;

        List<Criteria> filters = new ArrayList<>();

        if (search != null && !search.isBlank()) {
            filters.add(new Criteria().orOperator(
                    Criteria.where("title").regex(search, "i"),
                    Criteria.where("author").regex(search, "i")));
        }
        if (genre != null && !genre.isBlank()) {
            filters.add(Criteria.where("genre").regex("^" + genre + "$", "i"));
        }

        Criteria criteria = filters.isEmpty()
                ? new Criteria()
                : new Criteria().andOperator(filters.toArray(new Criteria[0]));

        Query query = new Query(criteria)
                .with(pageable)
                .skip(skip)
                .limit((int) pageSize);

        List<BookDocument> docs = mongoTemplate.find(query, BookDocument.class);
        List<Book> books = docs.stream().map(this::toBook).toList();
        long total = mongoTemplate.count(new Query(criteria), BookDocument.class);

        return new PageImpl<>(books, pageable, total);
    }

    @Override
    public List<Book> findAvailableByOwner(String ownerId) {
        Query query = new Query(Criteria.where("uploadedBy").is(ownerId).and("available").is(true));
        return mongoTemplate.find(query, BookDocument.class).stream().map(this::toBook).toList();
    }

    @Override
    public List<Book> findBorrowedByUser(String userId) {
        Query query = new Query(Criteria.where("borrowedBy").is(userId));
        return mongoTemplate.find(query, BookDocument.class).stream().map(this::toBook).toList();
    }

    @Override
    public Optional<Book> findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        BookDocument doc = mongoTemplate.findOne(query, BookDocument.class);
        return Optional.ofNullable(doc).map(this::toBook);
    }

    @Override
    public Book save(Book book) {
        BookDocument doc = new BookDocument(
                book.getId(), book.getTitle(), book.getAuthor(),
                book.getDescription(), book.getImage(), book.getPageCount(),
                book.getLanguage(), book.getUploadedBy(), book.getLoanDays(), book.getGenre());
        BookDocument saved = mongoTemplate.save(doc);
        return toBook(saved);
    }

    @Override
    public Book lend(String bookId, String borrowerId) {
        Query query = new Query(Criteria.where("id").is(bookId));
        Update update = new Update().set("available", false).set("borrowedBy", borrowerId);
        mongoTemplate.updateFirst(query, update, BookDocument.class);
        return toBook(mongoTemplate.findOne(query, BookDocument.class));
    }

    @Override
    public Book returnBook(String bookId) {
        Query query = new Query(Criteria.where("id").is(bookId));
        Update update = new Update().set("available", true).unset("borrowedBy");
        mongoTemplate.updateFirst(query, update, BookDocument.class);
        return toBook(mongoTemplate.findOne(query, BookDocument.class));
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, BookDocument.class);
    }

    @Override
    public Review addReview(String bookId, Review review) {
        ReviewDocument reviewDoc = new ReviewDocument(
                review.getId(), review.getAuthor(), review.getText(),
                review.getStars(), review.getCreatedAt());
        Query query = new Query(Criteria.where("id").is(bookId));
        Update update = new Update().push("reviews", reviewDoc);
        mongoTemplate.updateFirst(query, update, BookDocument.class);
        return review;
    }

    @Override
    public List<Review> findReviewsByBookId(String bookId) {
        Query query = new Query(Criteria.where("id").is(bookId));
        BookDocument doc = mongoTemplate.findOne(query, BookDocument.class);
        if (doc == null || doc.getReviews() == null) return new ArrayList<>();
        return doc.getReviews().stream().map(this::toReview).toList();
    }

    private Book toBook(BookDocument doc) {
        return new Book(
                doc.getId(), doc.getTitle(), doc.getAuthor(),
                doc.getDescription(), doc.getImage(), doc.getPageCount(),
                doc.getLanguage(), doc.getUploadedBy(), doc.getLoanDays(), doc.getGenre(),
                doc.isAvailable(), doc.getBorrowedBy());
    }

    private Review toReview(ReviewDocument doc) {
        return new Review(
                doc.getId(), doc.getAuthor(), doc.getText(),
                doc.getStars(), doc.getCreatedAt());
    }
}