package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Publisher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    @OneToMany
    private List<Book> books;
}
