package com.example.demo.controlers.resources;

import com.example.demo.entities.Book;
import com.example.demo.entities.User;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Resource
@Getter
@Setter
public class TransactionRes {
    private Long id;
    private Date borrowDate;
    private Date returnDate;
    private boolean isOverdue;
    private User user;
    private Book book;
}
