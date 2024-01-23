package com.example.demo.controlers;

import com.example.demo.controlers.resources.TransactionRes;
import com.example.demo.services.TransacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class TransactionController {
    private final TransacionService service;

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.ok(service.AllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRes transaction) {
        TransactionRes saved = service.save(transaction);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/transactions/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionRes transaction, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(transaction, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
