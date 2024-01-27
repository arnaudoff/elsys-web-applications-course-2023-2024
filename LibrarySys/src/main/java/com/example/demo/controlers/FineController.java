package com.example.demo.controlers;

import com.example.demo.controlers.resources.FineRes;
import com.example.demo.services.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class FineController {
    private final FineService service;

    @GetMapping
    public ResponseEntity<?> getAllFines() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFineById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createFine(@RequestBody FineRes fine) {
        FineRes saved = service.save(fine);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/fines/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFine(@RequestBody FineRes fine, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(fine, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFine(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
