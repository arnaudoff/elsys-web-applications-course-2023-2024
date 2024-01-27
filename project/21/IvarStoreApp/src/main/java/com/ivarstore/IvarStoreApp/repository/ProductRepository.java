package com.ivarstore.IvarStoreApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivarstore.IvarStoreApp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
