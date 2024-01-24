package com.ivarstore.IvarStoreApp.controller;

import com.ivarstore.IvarStoreApp.controller.resources.CustomerResource;
import com.ivarstore.IvarStoreApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResource>> findAll() {
        List<CustomerResource> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResource> findById(@PathVariable long id) {
        CustomerResource customer = customerService.getById(id);
        return customer != null ?
                new ResponseEntity<>(customer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CustomerResource> create(@RequestBody CustomerResource customerResource) {
        CustomerResource createdCustomer = customerService.save(customerResource);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResource> update(@PathVariable long id, @RequestBody CustomerResource customerResource) {
        CustomerResource updatedCustomer = customerService.update(customerResource, id);
        return updatedCustomer != null ?
                new ResponseEntity<>(updatedCustomer, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
