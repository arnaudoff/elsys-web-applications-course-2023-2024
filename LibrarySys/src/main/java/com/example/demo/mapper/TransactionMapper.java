package com.example.demo.mapper;

import com.example.demo.entities.BorrowingTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.example.demo.controlers.resources.TransactionRes;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper TRANSACTION_MAPPER = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "book.name", source = "resource.book")
    BorrowingTransaction transactionToResuorce(TransactionRes resource);

    @Mapping(target = "book", source = "transaction.book.name")
    TransactionRes transactionToTransactionRes(BorrowingTransaction transaction);

    List<TransactionRes> toTransactionResList(List<BorrowingTransaction> all);
}
