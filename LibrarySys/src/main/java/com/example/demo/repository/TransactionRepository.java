package com.example.demo.repository;

import com.example.demo.entities.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    BorrowingTransaction getTransactionByBookId(Long bookId);
}
