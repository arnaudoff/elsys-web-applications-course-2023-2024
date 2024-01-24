package com.example.restaurant.controller;

import com.example.restaurant.controller.resources.OrderResourceRequest;
import com.example.restaurant.controller.resources.OrderResourceResponse;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderResourceRequest orderResource) {
        OrderResourceResponse createdOrderResource = orderService.save(orderResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/orders/{id}")
                        .buildAndExpand(createdOrderResource.getId())
                        .toUri()
        ).body(createdOrderResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OrderResourceRequest orderResource) {
        OrderResourceResponse updatedOrderResource = orderService.update(id, orderResource);

        return ResponseEntity.ok(updatedOrderResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
