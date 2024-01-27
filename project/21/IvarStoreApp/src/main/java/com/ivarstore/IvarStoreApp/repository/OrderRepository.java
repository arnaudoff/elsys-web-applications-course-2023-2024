package com.ivarstore.IvarStoreApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivarstore.IvarStoreApp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
