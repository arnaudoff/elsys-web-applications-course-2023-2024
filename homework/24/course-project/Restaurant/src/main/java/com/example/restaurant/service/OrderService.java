package com.example.restaurant.service;

import com.example.restaurant.controller.resources.OrderResourceRequest;
import com.example.restaurant.controller.resources.OrderResourceResponse;

import java.util.List;

public interface OrderService {
    List<OrderResourceResponse> getAll();
    OrderResourceResponse getById(Long id);
    OrderResourceResponse save(OrderResourceRequest orderResource);
    OrderResourceResponse update(Long id, OrderResourceRequest orderResource);
    void delete(Long id);
}
