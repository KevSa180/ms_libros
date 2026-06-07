package com.maovares.ms_books.loan.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.loan.application.port.in.ReturnLoanCommand;
import com.maovares.ms_books.loan.application.port.out.LoanRepository;
import com.maovares.ms_books.loan.domain.exception.LoanNotFoundException;
import com.maovares.ms_books.loan.domain.model.Loan;

@Service
public class ReturnLoanService implements ReturnLoanCommand {

    private final LoanRepository loanRepository;

    public ReturnLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan execute(String loanId, String returnConditionNotes, Integer rating, String comment) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan " + loanId + " not found"));
        loan.setStatus("returned");
        loan.setReturnedAt(LocalDateTime.now());
        loan.setReturnConditionNotes(returnConditionNotes);
        loan.setRating(rating);
        loan.setComment(comment);
        return loanRepository.save(loan);
    }
}
