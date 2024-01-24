package com.ivarstore.IvarStoreApp.service.impl;

import com.ivarstore.IvarStoreApp.controller.resources.OrderResource;
import com.ivarstore.IvarStoreApp.entity.Order;
import com.ivarstore.IvarStoreApp.mapper.OrderMapper;
import com.ivarstore.IvarStoreApp.repository.OrderRepository;
import com.ivarstore.IvarStoreApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResource> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResources(orders);
    }

    @Override
    public OrderResource getById(long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return order != null ? orderMapper.toResource(order) : null;
    }

    @Override
    public OrderResource save(OrderResource orderResource) {
        Order order = orderMapper.toEntity(orderResource);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResource(savedOrder);
    }

    @Override
    public OrderResource update(OrderResource orderResource, long id) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            orderMapper.updateEntityFromResource(orderResource, existingOrder);
            Order savedOrder = orderRepository.save(existingOrder);
            return orderMapper.toResource(savedOrder);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
