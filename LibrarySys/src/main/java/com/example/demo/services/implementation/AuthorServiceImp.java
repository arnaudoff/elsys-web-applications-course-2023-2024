package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.AuthorRes;
import com.example.demo.entities.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.services.AuthorService;
import com.example.demo.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.mapper.AuthorMapper.AUTHOR_MAPPER;
@Service
@RequiredArgsConstructor
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Override
    public List<AuthorRes> AllAuthors() {
        return AUTHOR_MAPPER.toAuthorResList(authorRepository.findAll());
    }

    @Override
    public AuthorRes findById(Long id) {
        return AUTHOR_MAPPER.authorToAuthorRes(authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found")));
    }

    @Override
    public AuthorRes save(AuthorRes resource) {
        Author authorTemp = authorRepository.save(AUTHOR_MAPPER.authorToResuorce(resource));
        resource.setId(authorTemp.getId());
        resource.setBooks(null);

        return resource;
    }

    @Override
    public AuthorRes update(Author resource, Long id) {
        Author authorTemp = authorRepository.getReferenceById(id);

            authorTemp.setName(authorTemp.getName());
            authorTemp.setBooks(authorTemp.getBooks());
            bookService.updateAll(resource.getBooks(), authorTemp.getBooks());

        return AUTHOR_MAPPER.authorToAuthorRes(authorRepository.save(authorTemp));
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

}
