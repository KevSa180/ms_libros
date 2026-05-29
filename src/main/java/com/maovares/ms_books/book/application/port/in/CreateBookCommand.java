package com.maovares.ms_books.book.application.port.in;

import com.maovares.ms_books.book.domain.model.Book;

public interface CreateBookCommand {
    Book execute(String title, String author, String description, String image, int pageCount, String language, String uploadedBy);
}
