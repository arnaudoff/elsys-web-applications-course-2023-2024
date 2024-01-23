package com.example.webdevshoes.controller;

import com.example.webdevshoes.DTO.ReviewDTO;
import com.example.webdevshoes.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(reviewService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<?> getByRating(@PathVariable Integer rating) {
        return ResponseEntity.ok(reviewService.getAllByRating(rating));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getByUserId(@PathVariable String username) {
        return ResponseEntity.ok(reviewService.getAllByUser(username));
    }

    @GetMapping("/shoe/{model}")
    public ResponseEntity<?> getByShoeId(@PathVariable String model) {
        return ResponseEntity.ok(reviewService.getAllByShoe(model));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReviewDTO review) {
        ReviewDTO savedReview = reviewService.save(review);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/reviews/{id}")
                        .buildAndExpand(savedReview.getId())
                        .toUri()
        ).body(savedReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ReviewDTO review, @PathVariable Long id) {
        return ResponseEntity.ok(reviewService.update(review, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
