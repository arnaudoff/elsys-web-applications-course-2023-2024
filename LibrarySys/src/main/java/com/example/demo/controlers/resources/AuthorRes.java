package com.example.demo.controlers.resources;

import com.example.demo.entities.Book;
import jakarta.annotation.Resource;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Resource
@Getter
@Setter
public class AuthorRes {
    private Long id;
    private String name;
    private List<Book> books;

}
