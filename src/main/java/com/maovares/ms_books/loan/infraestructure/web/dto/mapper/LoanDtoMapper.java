package com.maovares.ms_books.loan.infraestructure.web.dto.mapper;

import com.maovares.ms_books.loan.domain.model.Loan;
import com.maovares.ms_books.loan.infraestructure.web.dto.LoanResponseDto;

public class LoanDtoMapper {
    public static LoanResponseDto toResponse(Loan loan) {
        return LoanResponseDto.fromDomain(loan);
    }
}
