package com.maovares.ms_books.loan.infraestructure.web.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.maovares.ms_books.loan.domain.exception.LoanNotFoundException;

@ControllerAdvice(basePackages = "com.maovares.ms_books.loan.infraestructure.web")
public class LoanExceptionHandler {

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<String> handleLoanNotFound(LoanNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
