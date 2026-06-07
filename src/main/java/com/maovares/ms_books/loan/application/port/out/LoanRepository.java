package com.maovares.ms_books.loan.application.port.out;

import java.util.List;
import java.util.Optional;

import com.maovares.ms_books.loan.domain.model.Loan;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(String id);
    List<Loan> findByUser(String userId);
}
