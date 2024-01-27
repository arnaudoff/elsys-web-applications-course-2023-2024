package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    private Author author;
    private String genre;
    @ManyToOne
    private Publisher publisher;
    private int year;
    private boolean isAvailable;
    @ManyToOne
    private User borrower;

    public boolean getAvailable() {
        return isAvailable;
    }
}
