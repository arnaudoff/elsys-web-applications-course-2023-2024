package com.ivarstore.IvarStoreApp.controller;

import com.ivarstore.IvarStoreApp.controller.resources.OrderResource;
import com.ivarstore.IvarStoreApp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResource>> findAll() {
        List<OrderResource> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResource> findById(@PathVariable long id) {
        OrderResource order = orderService.getById(id);
        return order != null ?
                new ResponseEntity<>(order, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderResource> create(@RequestBody OrderResource orderResource) {
        OrderResource createdOrder = orderService.save(orderResource);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResource> update(@PathVariable long id, @RequestBody OrderResource orderResource) {
        OrderResource updatedOrder = orderService.update(orderResource, id);
        return updatedOrder != null ?
                new ResponseEntity<>(updatedOrder, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
