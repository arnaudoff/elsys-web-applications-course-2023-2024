package com.example.demo.services;

import com.example.demo.controlers.resources.AuthorRes;
import com.example.demo.entities.Author;

import java.util.List;


public interface AuthorService {
    List<AuthorRes> AllAuthors();

    AuthorRes findById(Long id);

    AuthorRes save(AuthorRes author);

    AuthorRes update(Author author, Long id);

    void deleteById(Long id);
}
