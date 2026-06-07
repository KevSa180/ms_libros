package com.maovares.ms_books.loan.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.loan.application.port.in.GetLoansQuery;
import com.maovares.ms_books.loan.application.port.out.LoanRepository;
import com.maovares.ms_books.loan.domain.model.Loan;

@Service
public class GetLoansService implements GetLoansQuery {

    private final LoanRepository loanRepository;

    public GetLoansService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> byUser(String userId) {
        return loanRepository.findByUser(userId);
    }
}
