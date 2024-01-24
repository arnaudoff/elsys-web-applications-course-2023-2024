package com.ivarstore.IvarStoreApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivarstore.IvarStoreApp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
