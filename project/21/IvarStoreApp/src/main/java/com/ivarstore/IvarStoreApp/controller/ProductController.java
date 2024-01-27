package com.ivarstore.IvarStoreApp.controller;

import com.ivarstore.IvarStoreApp.controller.resources.ProductResource;
import com.ivarstore.IvarStoreApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResource>> findAll() {
        List<ProductResource> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> findById(@PathVariable long id) {
        ProductResource product = productService.getById(id);
        return product != null ?
                new ResponseEntity<>(product, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductResource> create(@RequestBody ProductResource productResource) {
        ProductResource createdProduct = productService.save(productResource);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> update(@PathVariable long id, @RequestBody ProductResource productResource) {
        ProductResource updatedProduct = productService.update(productResource, id);
        return updatedProduct != null ?
                new ResponseEntity<>(updatedProduct, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
