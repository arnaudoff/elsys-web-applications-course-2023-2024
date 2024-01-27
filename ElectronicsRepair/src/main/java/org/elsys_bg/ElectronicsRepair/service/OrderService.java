package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.entity.Order;

import java.util.List;

public interface OrderService{
    List<OrderResource> findAll();

    OrderResource save(Order order);

    void delete(Order order);

    OrderResource updateOrder(Order order);
}