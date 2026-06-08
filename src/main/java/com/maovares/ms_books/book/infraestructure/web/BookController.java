package com.maovares.ms_books.book.infraestructure.web;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.maovares.ms_books.book.application.port.in.CreateBookCommand;
import com.maovares.ms_books.book.application.port.in.CreateReviewCommand;
import com.maovares.ms_books.book.application.port.in.DeleteBookCommand;
import com.maovares.ms_books.book.application.port.in.GetAvailableBooksQuery;
import com.maovares.ms_books.book.application.port.in.GetBookQuery;
import com.maovares.ms_books.book.application.port.in.GetBooksQuery;
import com.maovares.ms_books.book.application.port.in.GetBorrowedBooksQuery;
import com.maovares.ms_books.book.application.port.in.GetReviewsQuery;
import com.maovares.ms_books.book.application.port.in.LendBookCommand;
import com.maovares.ms_books.book.application.port.in.ReturnBookCommand;
import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.domain.model.Review;
import com.maovares.ms_books.book.infraestructure.web.dto.BookResponseDto;
import com.maovares.ms_books.book.infraestructure.web.dto.CreateBookDto;
import com.maovares.ms_books.book.infraestructure.web.dto.CreateReviewDto;
import com.maovares.ms_books.book.infraestructure.web.dto.LendBookDto;
import com.maovares.ms_books.book.infraestructure.web.dto.PagedResponseDto;
import com.maovares.ms_books.book.infraestructure.web.dto.ReviewResponseDto;
import com.maovares.ms_books.book.infraestructure.web.dto.mapper.BookDtoMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Books", description = "API for book management")
@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final GetBooksQuery getBooksQuery;
    private final GetBookQuery getBookQuery;
    private final CreateBookCommand createBookCommand;
    private final DeleteBookCommand deleteBookCommand;
    private final CreateReviewCommand createReviewCommand;
    private final GetReviewsQuery getReviewsQuery;
    private final LendBookCommand lendBookCommand;
    private final ReturnBookCommand returnBookCommand;
    private final GetAvailableBooksQuery getAvailableBooksQuery;
    private final GetBorrowedBooksQuery getBorrowedBooksQuery;

    public BookController(GetBooksQuery getBooksQuery, GetBookQuery getBookQuery,
            CreateBookCommand createBookCommand, DeleteBookCommand deleteBookCommand,
            CreateReviewCommand createReviewCommand, GetReviewsQuery getReviewsQuery,
            LendBookCommand lendBookCommand, ReturnBookCommand returnBookCommand,
            GetAvailableBooksQuery getAvailableBooksQuery, GetBorrowedBooksQuery getBorrowedBooksQuery) {
        this.getBooksQuery = getBooksQuery;
        this.getBookQuery = getBookQuery;
        this.createBookCommand = createBookCommand;
        this.deleteBookCommand = deleteBookCommand;
        this.createReviewCommand = createReviewCommand;
        this.getReviewsQuery = getReviewsQuery;
        this.lendBookCommand = lendBookCommand;
        this.returnBookCommand = returnBookCommand;
        this.getAvailableBooksQuery = getAvailableBooksQuery;
        this.getBorrowedBooksQuery = getBorrowedBooksQuery;
    }

    @Operation(summary = "Get paginated book list")
    @ApiResponse(responseCode = "200", description = "Paginated book list", content = @Content(schema = @Schema(implementation = PagedResponseDto.class)))
    @GetMapping
    public PagedResponseDto<BookResponseDto> getBooks(
            @ParameterObject Pageable pageable,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String genre) {
        Page<Book> books = getBooksQuery.execute(pageable, search, genre);
        List<BookResponseDto> content = books.getContent().stream()
                .map(BookDtoMapper::toResponse).toList();
        return new PagedResponseDto<>(content, books.getNumber(), books.getSize(),
                books.getTotalElements(), books.getTotalPages());
    }

    @Operation(summary = "Get book by ID")
    @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = BookResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable String id) {
        Book book = getBookQuery.execute(id);
        return BookDtoMapper.toResponse(book);
    }

    @Operation(summary = "Create a new book")
    @ApiResponse(responseCode = "201", description = "Book created", content = @Content(schema = @Schema(implementation = BookResponseDto.class)))
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid CreateBookDto body) {
        Book book = createBookCommand.execute(
                body.title(), body.author(), body.description(), body.image(),
                body.pageCount(), body.language(), body.uploadedBy(),
                body.loanDays(), body.genre());
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDtoMapper.toResponse(book));
    }

    @Operation(summary = "Delete a book")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        deleteBookCommand.execute(id);
        return ResponseEntity.noContent().build();
    }

    // ── Chat — lending options ────────────────────────────────────────────────

    @Operation(summary = "Get available books by owner", description = "Returns the owner's books with available=true. Used for the lending dropdown in chat.")
    @ApiResponse(responseCode = "200", description = "List of available books")
    @GetMapping("/chat/available")
    public List<BookResponseDto> getAvailableBooks(@RequestParam String ownerId) {
        return getAvailableBooksQuery.execute(ownerId).stream()
                .map(BookDtoMapper::toResponse)
                .toList();
    }

    @Operation(summary = "Get books borrowed by a user", description = "Returns the books currently lent to a user. Used for the return dropdown in chat.")
    @ApiResponse(responseCode = "200", description = "List of borrowed books")
    @GetMapping("/chat/borrowed")
    public List<BookResponseDto> getBorrowedBooks(@RequestParam String userId) {
        return getBorrowedBooksQuery.execute(userId).stream()
                .map(BookDtoMapper::toResponse)
                .toList();
    }

    // ── Lending ──────────────────────────────────────────────────────────────

    @Operation(summary = "Lend a book", description = "Marks the book as unavailable and assigns it to the borrower.")
    @ApiResponse(responseCode = "200", description = "Book lent successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @ApiResponse(responseCode = "409", description = "Book is not available")
    @PostMapping("/{id}/lend")
    public ResponseEntity<BookResponseDto> lendBook(
            @PathVariable String id,
            @RequestBody @Valid LendBookDto body) {
        Book book = lendBookCommand.execute(id, body.borrowerId());
        return ResponseEntity.ok(BookDtoMapper.toResponse(book));
    }

    @Operation(summary = "Return a book", description = "Marks the book as available again.")
    @ApiResponse(responseCode = "200", description = "Book returned successfully")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @PostMapping("/{id}/return")
    public ResponseEntity<BookResponseDto> returnBook(@PathVariable String id) {
        Book book = returnBookCommand.execute(id);
        return ResponseEntity.ok(BookDtoMapper.toResponse(book));
    }

    // ── Reviews ──────────────────────────────────────────────────────────────

    @Operation(summary = "Get reviews for a book")
    @ApiResponse(responseCode = "200", description = "List of reviews")
    @GetMapping("/{id}/reviews")
    public List<ReviewResponseDto> getReviews(@PathVariable String id) {
        return getReviewsQuery.execute(id).stream()
                .map(ReviewResponseDto::fromDomain)
                .toList();
    }

    @Operation(summary = "Add a review to a book")
    @ApiResponse(responseCode = "201", description = "Review created")
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewResponseDto> addReview(
            @PathVariable String id,
            @RequestBody @Valid CreateReviewDto body) {
        Review review = createReviewCommand.execute(id, body.author(), body.text(), body.stars());
        return ResponseEntity.status(HttpStatus.CREATED).body(ReviewResponseDto.fromDomain(review));
    }
}