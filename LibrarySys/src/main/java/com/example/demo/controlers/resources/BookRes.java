package com.example.demo.controlers.resources;

import com.example.demo.entities.Author;
import com.example.demo.entities.Publisher;
import com.example.demo.entities.User;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;

@Resource
@Getter
@Setter
public class BookRes {
    private Long id;
    private String title;
    private Author author;
    private String genre;
    private Publisher publisher;
    private int year;
    private boolean isAvailable;
    private User borrower;

    public boolean getAvailable() {
        return isAvailable;
    }
}
