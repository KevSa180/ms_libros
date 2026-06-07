package com.maovares.ms_books.loan.application.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.maovares.ms_books.loan.application.port.in.CreateLoanCommand;
import com.maovares.ms_books.loan.application.port.out.LoanRepository;
import com.maovares.ms_books.loan.domain.model.Loan;

@Service
public class CreateLoanService implements CreateLoanCommand {

    private final LoanRepository loanRepository;

    public CreateLoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan execute(String bookId, String bookTitle, String bookAuthor,
            String lenderId, String borrowerId, int durationDays,
            String conditionNotes, String conditionPhotos) {
        String id = UUID.randomUUID().toString();
        int days = durationDays > 0 ? durationDays : 14;
        Loan loan = new Loan(id, bookId, bookTitle, bookAuthor, lenderId, borrowerId,
                days, conditionNotes, conditionPhotos, "pending",
                LocalDateTime.now(), null, null, null, null, null);
        return loanRepository.save(loan);
    }
}
