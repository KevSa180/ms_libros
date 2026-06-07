package com.maovares.ms_books.loan.infraestructure.persistence.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.maovares.ms_books.loan.application.port.out.LoanRepository;
import com.maovares.ms_books.loan.domain.model.Loan;

@Component
public class LoanMongoAdapter implements LoanRepository {

    private final LoanMongoRepository repo;

    public LoanMongoAdapter(LoanMongoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Loan save(Loan loan) {
        LoanDocument doc = toDocument(loan);
        return toDomain(repo.save(doc));
    }

    @Override
    public Optional<Loan> findById(String id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Loan> findByUser(String userId) {
        return repo.findByLenderIdOrBorrowerId(userId, userId)
                .stream().map(this::toDomain).toList();
    }

    private LoanDocument toDocument(Loan l) {
        return new LoanDocument(l.getId(), l.getBookId(), l.getBookTitle(), l.getBookAuthor(),
                l.getLenderId(), l.getBorrowerId(), l.getDurationDays(), l.getConditionNotes(),
                l.getConditionPhotos(), l.getStatus(), l.getCreatedAt(), l.getAcceptedAt(),
                l.getReturnedAt(), l.getReturnConditionNotes(), l.getRating(), l.getComment());
    }

    private Loan toDomain(LoanDocument d) {
        return new Loan(d.getId(), d.getBookId(), d.getBookTitle(), d.getBookAuthor(),
                d.getLenderId(), d.getBorrowerId(), d.getDurationDays(), d.getConditionNotes(),
                d.getConditionPhotos(), d.getStatus(), d.getCreatedAt(), d.getAcceptedAt(),
                d.getReturnedAt(), d.getReturnConditionNotes(), d.getRating(), d.getComment());
    }
}
