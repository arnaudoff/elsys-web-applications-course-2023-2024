package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.entities.Author;
import com.example.demo.controlers.resources.AuthorRes;

import java.util.List;


@Mapper(uses = BookMapper.class)
public interface AuthorMapper {
    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);

    Author authorToResuorce(AuthorRes resource);

    AuthorRes authorToAuthorRes(Author author);

    List<AuthorRes> toAuthorResList(List<Author> authors);
}