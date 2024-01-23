package com.example.demo.controlers;

import com.example.demo.controlers.resources.PublisherRes;
import com.example.demo.services.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping
    public ResponseEntity<?> getAllPublishers() {
        return ResponseEntity.ok(service.AllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createPublisher(@RequestBody PublisherRes publisher) {
        PublisherRes saved = service.save(publisher);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/publishers/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@RequestBody PublisherRes publisher, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(publisher, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
