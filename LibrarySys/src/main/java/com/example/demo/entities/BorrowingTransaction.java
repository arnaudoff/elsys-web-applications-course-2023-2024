package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class BorrowingTransaction {
    @Id
    @GeneratedValue
    private Long id;
    private Date borrowDate;
    private Date returnDate;
    private boolean isOverdue;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
}
