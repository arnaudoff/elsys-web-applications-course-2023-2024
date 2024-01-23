package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    //borrowedBooks
    @ManyToMany
    private List<Book> borrowedBooks;
    @OneToMany
    private List<Fine> fines;
}
