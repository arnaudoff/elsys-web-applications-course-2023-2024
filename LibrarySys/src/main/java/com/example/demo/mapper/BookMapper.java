package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.entities.Book;
import com.example.demo.controlers.resources.BookRes;

import java.util.List;

@Mapper(uses = {AuthorMapper.class, PublisherMapper.class, UserMapper.class})
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    Book bookToResuorce(BookRes resource);
    BookRes bookToBookRes(Book book);
    List<BookRes> toBookResList(List<Book> books);
}
