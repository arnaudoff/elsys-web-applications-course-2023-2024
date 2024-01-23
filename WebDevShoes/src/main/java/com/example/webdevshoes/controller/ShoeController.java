package com.example.webdevshoes.controller;

import com.example.webdevshoes.DTO.ShoeDTO;
import com.example.webdevshoes.service.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/shoes")
@RequiredArgsConstructor
public class ShoeController {
    private final ShoeService shoeService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(shoeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(shoeService.getById(id));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<?> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(shoeService.getByBrand(brand));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<?> getBySize(@PathVariable String size) {
        return ResponseEntity.ok(shoeService.getBySize(size));
    }

    @GetMapping("/price-below/{maxPrice}")
    public ResponseEntity<?> getByPriceLessThan(@PathVariable BigDecimal maxPrice) {
        return ResponseEntity.ok(shoeService.getByPriceLessThan(maxPrice));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ShoeDTO shoe) {
        ShoeDTO savedShoe = shoeService.save(shoe);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/shoes/{id}")
                        .buildAndExpand(savedShoe.getId())
                        .toUri()
        ).body(savedShoe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ShoeDTO shoe, @PathVariable Long id) {
        return ResponseEntity.ok(shoeService.update(shoe, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        shoeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
