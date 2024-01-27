package com.example.demo.controlers;

import com.example.demo.controlers.resources.AuthorRes;
import com.example.demo.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(service.AllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRes author) {
        AuthorRes saved = service.save(author);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/authors/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorRes author, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(author, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
