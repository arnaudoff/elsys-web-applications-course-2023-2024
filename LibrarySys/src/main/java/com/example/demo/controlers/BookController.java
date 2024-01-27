package com.example.demo.controlers;

import com.example.demo.controlers.resources.BookRes;
import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(service.AllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRes book) {
        BookRes saved = service.save(book);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/books/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookRes book, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(book, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
