package com.maovares.ms_books.loan.infraestructure.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maovares.ms_books.loan.application.port.in.CreateLoanCommand;
import com.maovares.ms_books.loan.application.port.in.GetLoansQuery;
import com.maovares.ms_books.loan.application.port.in.RespondLoanCommand;
import com.maovares.ms_books.loan.application.port.in.ReturnLoanCommand;
import com.maovares.ms_books.loan.domain.model.Loan;
import com.maovares.ms_books.loan.infraestructure.web.dto.CreateLoanDto;
import com.maovares.ms_books.loan.infraestructure.web.dto.LoanResponseDto;
import com.maovares.ms_books.loan.infraestructure.web.dto.ReturnLoanDto;
import com.maovares.ms_books.loan.infraestructure.web.dto.mapper.LoanDtoMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Loans", description = "API for book loan management")
@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    private final CreateLoanCommand createLoanCommand;
    private final RespondLoanCommand respondLoanCommand;
    private final ReturnLoanCommand returnLoanCommand;
    private final GetLoansQuery getLoansQuery;

    public LoanController(CreateLoanCommand createLoanCommand, RespondLoanCommand respondLoanCommand,
            ReturnLoanCommand returnLoanCommand, GetLoansQuery getLoansQuery) {
        this.createLoanCommand = createLoanCommand;
        this.respondLoanCommand = respondLoanCommand;
        this.returnLoanCommand = returnLoanCommand;
        this.getLoansQuery = getLoansQuery;
    }

    @Operation(summary = "Create a loan request (condition report)")
    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@RequestBody @Valid CreateLoanDto body) {
        Loan loan = createLoanCommand.execute(body.bookId(), body.bookTitle(), body.bookAuthor(),
                body.lenderId(), body.borrowerId(), body.durationDays(),
                body.conditionNotes(), body.conditionPhotos());
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanDtoMapper.toResponse(loan));
    }

    @Operation(summary = "Accept a loan")
    @PostMapping("/{id}/accept")
    public LoanResponseDto accept(@PathVariable String id) {
        return LoanDtoMapper.toResponse(respondLoanCommand.accept(id));
    }

    @Operation(summary = "Decline a loan")
    @PostMapping("/{id}/decline")
    public LoanResponseDto decline(@PathVariable String id) {
        return LoanDtoMapper.toResponse(respondLoanCommand.decline(id));
    }

    @Operation(summary = "Return a loan (condition + rating + comment)")
    @PostMapping("/{id}/return")
    public LoanResponseDto returnLoan(@PathVariable String id, @RequestBody @Valid ReturnLoanDto body) {
        Loan loan = returnLoanCommand.execute(id, body.returnConditionNotes(), body.rating(), body.comment());
        return LoanDtoMapper.toResponse(loan);
    }

    @Operation(summary = "Get loans for a user (as lender or borrower)")
    @GetMapping("/user/{userId}")
    public List<LoanResponseDto> byUser(@PathVariable String userId) {
        return getLoansQuery.byUser(userId).stream().map(LoanDtoMapper::toResponse).toList();
    }
}
