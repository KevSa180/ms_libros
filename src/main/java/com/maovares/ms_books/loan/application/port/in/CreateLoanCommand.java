package com.maovares.ms_books.loan.application.port.in;

import com.maovares.ms_books.loan.domain.model.Loan;

public interface CreateLoanCommand {
    Loan execute(String bookId, String bookTitle, String bookAuthor,
            String lenderId, String borrowerId, int durationDays,
            String conditionNotes, String conditionPhotos);
}
