package com.maovares.ms_books.loan.application.port.in;

import java.util.List;

import com.maovares.ms_books.loan.domain.model.Loan;

public interface GetLoansQuery {
    List<Loan> byUser(String userId);
}
