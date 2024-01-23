package com.example.demo.services;

import com.example.demo.controlers.resources.BookRes;
import com.example.demo.entities.Book;
import java.util.List;


public interface BookService {
    List<BookRes> AllBooks();
    BookRes findById(Long id);
    BookRes save(BookRes book);
    BookRes update(Book book, Long id);
    void deleteById(Long id);
    void updateAll(List<Book> books, List<Book> books1);
}
