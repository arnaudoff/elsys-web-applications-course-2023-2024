package com.example.demo.controlers.resources;

import com.example.demo.entities.Book;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Resource
@Getter
@Setter
public class PublisherRes {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private List<Book> books;



}
