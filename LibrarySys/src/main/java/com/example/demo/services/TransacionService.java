package com.example.demo.services;

import com.example.demo.controlers.resources.TransactionRes;
import java.util.List;

public interface TransacionService {
    List<TransactionRes> AllTransactions();
    TransactionRes findById(Long id);
    TransactionRes save(TransactionRes transaction);
    TransactionRes update(TransactionRes transaction, Long id);
    void deleteById(Long id);
}
