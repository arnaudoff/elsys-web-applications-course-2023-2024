package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService{
    List<OrderStatusResource> findAll();

    OrderStatusResource save(OrderStatus orderStatus);

    void delete(OrderStatus orderStatus);

    OrderStatusResource updateOrderStatus(OrderStatus orderStatus);
}