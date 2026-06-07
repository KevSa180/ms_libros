package com.maovares.ms_books.loan.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.loan.application.port.in.RespondLoanCommand;
import com.maovares.ms_books.loan.application.port.out.LoanRepository;
import com.maovares.ms_books.loan.domain.exception.LoanNotFoundException;
import com.maovares.ms_books.loan.domain.model.Loan;

@Service
public class RespondLoanService implements RespondLoanCommand {

    private final LoanRepository loanRepository;

    public RespondLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan accept(String loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan " + loanId + " not found"));
        loan.setStatus("active");
        loan.setAcceptedAt(LocalDateTime.now());
        return loanRepository.save(loan);
    }

    @Override
    public Loan decline(String loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan " + loanId + " not found"));
        loan.setStatus("declined");
        return loanRepository.save(loan);
    }
}
