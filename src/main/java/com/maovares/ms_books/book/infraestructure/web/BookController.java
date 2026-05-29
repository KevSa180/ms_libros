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
import org.springframework.web.bind.annotation.RestController;

import com.maovares.ms_books.book.application.port.in.CreateBookCommand;
import com.maovares.ms_books.book.application.port.in.DeleteBookCommand;
import com.maovares.ms_books.book.application.port.in.GetBookQuery;
import com.maovares.ms_books.book.application.port.in.GetBooksQuery;
import com.maovares.ms_books.book.domain.model.Book;
import com.maovares.ms_books.book.infraestructure.web.dto.BookResponseDto;
import com.maovares.ms_books.book.infraestructure.web.dto.CreateBookDto;
import com.maovares.ms_books.book.infraestructure.web.dto.PagedResponseDto;
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

    public BookController(GetBooksQuery getBooksQuery, GetBookQuery getBookQuery,
            CreateBookCommand createBookCommand, DeleteBookCommand deleteBookCommand) {
        this.getBooksQuery = getBooksQuery;
        this.getBookQuery = getBookQuery;
        this.createBookCommand = createBookCommand;
        this.deleteBookCommand = deleteBookCommand;
    }

    @Operation(summary = "Get paginated book list", description = "Returns a paginated book list, supports query params like: page, size, sort.")
    @ApiResponse(responseCode = "200", description = "Paginated book list", content = @Content(schema = @Schema(implementation = PagedResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Invalid params")
    @GetMapping
    public PagedResponseDto<BookResponseDto> getBooks(@ParameterObject Pageable pageable) {
        Page<Book> books = getBooksQuery.execute(pageable);

        List<BookResponseDto> content = books.getContent()
                .stream()
                .map(BookDtoMapper::toResponse)
                .toList();

        return new PagedResponseDto<>(
                content,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages());
    }

    @Operation(summary = "Get book by ID", description = "Returns a single book by its ID.")
    @ApiResponse(responseCode = "200", description = "Book found", content = @Content(schema = @Schema(implementation = BookResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Book not found")
    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable String id) {
        Book book = getBookQuery.execute(id);
        return BookDtoMapper.toResponse(book);
    }

    @Operation(summary = "Create a new book", description = "Creates a new book with the provided details.")
    @ApiResponse(responseCode = "201", description = "Book created", content = @Content(schema = @Schema(implementation = BookResponseDto.class)))
    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody @Valid CreateBookDto body) {
        Book book = createBookCommand.execute(
                body.title(), body.author(), body.description(), body.image(),
                body.pageCount(), body.language(), body.uploadedBy());
        return ResponseEntity.status(HttpStatus.CREATED).body(BookDtoMapper.toResponse(book));
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @ApiResponse(responseCode = "204", description = "Book deleted")
    @ApiResponse(responseCode = "404", description = "Book not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        deleteBookCommand.execute(id);
        return ResponseEntity.noContent().build();
    }
}
