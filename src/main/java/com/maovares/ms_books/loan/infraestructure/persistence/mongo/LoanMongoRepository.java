package com.maovares.ms_books.loan.infraestructure.persistence.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanMongoRepository extends MongoRepository<LoanDocument, String> {
    List<LoanDocument> findByLenderIdOrBorrowerId(String lenderId, String borrowerId);
}
