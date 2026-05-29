package com.maovares.ms_books.book.infraestructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookMongoRepository extends MongoRepository<BookDocument, String> {
}
