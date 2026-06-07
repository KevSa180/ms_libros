package com.maovares.ms_books.loan.application.port.in;

import com.maovares.ms_books.loan.domain.model.Loan;

public interface RespondLoanCommand {
    Loan accept(String loanId);
    Loan decline(String loanId);
}
