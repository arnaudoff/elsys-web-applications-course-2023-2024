package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Fine {
    @Id
    @GeneratedValue
    private Long id;
    private double amount;
    private Date dueDate;
    private boolean isPaid;
    @ManyToOne
    private User user;
}
