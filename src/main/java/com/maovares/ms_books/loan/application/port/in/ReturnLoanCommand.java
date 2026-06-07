package com.maovares.ms_books.loan.application.port.in;

import com.maovares.ms_books.loan.domain.model.Loan;

public interface ReturnLoanCommand {
    Loan execute(String loanId, String returnConditionNotes, Integer rating, String comment);
}
