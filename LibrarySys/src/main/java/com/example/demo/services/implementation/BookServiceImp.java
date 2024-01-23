package com.example.demo.services.implementation;

import com.example.demo.controlers.resources.BookRes;
import com.example.demo.entities.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.services.AuthorService;
import com.example.demo.services.PublisherService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.services.BookService;
import java.util.List;

import static com.example.demo.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final UserService userService;
    @Override
    public List<BookRes> AllBooks() {
        return BOOK_MAPPER.toBookResList(bookRepository.findAll());
    }

    @Override
    public BookRes findById(Long id) {
        return BOOK_MAPPER.bookToBookRes(bookRepository.findById(id).orElse(null));
    }

    @Override
    public BookRes save(BookRes book) {
        Book bookTemp = bookRepository.save(BOOK_MAPPER.bookToResuorce(book));
        book.setId(bookTemp.getId());
        book.setAuthor(null);

        return book;
    }

    @Override
    public BookRes update(Book book, Long id) {
        Book bookTemp = bookRepository.getReferenceById(id);

            bookTemp.setTitle(book.getTitle());
            authorService.update(book.getAuthor(), book.getAuthor().getId());
            book.setYear(book.getYear());
            book.setGenre(book.getGenre());
            userService.update(book.getBorrower(), book.getBorrower().getId());
            publisherService.update(book.getPublisher(), book.getPublisher().getId());
            book.setAvailable(book.getAvailable());

        return BOOK_MAPPER.bookToBookRes(bookRepository.save(bookTemp));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void updateAll(List<Book> books, List<Book> books1) {
        for (Book book : books) {
            if (!books1.contains(book)) {
                bookRepository.save(book);
            }
        }
    }
}