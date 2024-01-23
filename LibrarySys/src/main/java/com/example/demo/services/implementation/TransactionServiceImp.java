package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.TransactionRes;
import com.example.demo.entities.BorrowingTransaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.services.TransacionService;
import java.util.List;

import static com.example.demo.mapper.TransactionMapper.TRANSACTION_MAPPER;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransacionService {
    private final TransactionRepository transactionRepository;
    private final BookService bookService;
    private final UserService userService;

    @Override
    public List<TransactionRes> AllTransactions() {
        return TRANSACTION_MAPPER.toTransactionResList(transactionRepository.findAll());
    }

    @Override
    public TransactionRes findById(Long id) {
        return TRANSACTION_MAPPER.transactionToTransactionRes(transactionRepository.findById(id).orElse(null));
    }

    @Override
    public TransactionRes save(TransactionRes transaction) {
        BorrowingTransaction borrowingTransaction = transactionRepository.save(TRANSACTION_MAPPER.transactionToResuorce(transaction));
        transaction.setId(borrowingTransaction.getId());
        transaction.setBook(null);
        transaction.setUser(null);

        return transaction;
    }

    @Override
    public TransactionRes update(TransactionRes transaction, Long id) {
        BorrowingTransaction borrowingTransaction = transactionRepository.getReferenceById(id);

        borrowingTransaction.setBorrowDate(transaction.getBorrowDate());
        borrowingTransaction.setReturnDate(transaction.getReturnDate());
        borrowingTransaction.setOverdue(transaction.isOverdue());
        bookService.update(transaction.getBook(), borrowingTransaction.getBook().getId());
        userService.update(transaction.getUser(), borrowingTransaction.getUser().getId());

        return TRANSACTION_MAPPER.transactionToTransactionRes(transactionRepository.save(borrowingTransaction));
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

}
