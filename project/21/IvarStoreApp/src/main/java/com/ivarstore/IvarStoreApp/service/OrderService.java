package com.ivarstore.IvarStoreApp.service;

import java.util.List;

import com.ivarstore.IvarStoreApp.controller.resources.OrderResource;

public interface OrderService {
    List<OrderResource> findAll(); 
    OrderResource getById(long id); 
    OrderResource save(OrderResource OrderResource);
    OrderResource update(OrderResource OrderResource, long id);
    void delete(long id);
}
