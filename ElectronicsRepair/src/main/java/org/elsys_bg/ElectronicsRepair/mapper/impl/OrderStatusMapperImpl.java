package org.elsys_bg.ElectronicsRepair.mapper.impl;

import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.elsys_bg.ElectronicsRepair.mapper.OrderStatusMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrderStatusProjection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OrderStatusMapperImpl implements OrderStatusMapper{
    @Override
    public OrderStatus fromOrderStatusResource(OrderStatusResource orderStatusResource){
        if(orderStatusResource == null){
            return null;
        }

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(orderStatusResource.getId());
        orderStatus.setOrderStatus(orderStatusResource.getOrderStatus());

        return orderStatus;
    }

    @Override
    public OrderStatusResource toOrderStatusResource(OrderStatus orderStatus){
        if(orderStatus == null){
            return null;
        }

        OrderStatusResource orderStatusResource = new OrderStatusResource();
        orderStatusResource.setId(orderStatus.getId());
        orderStatusResource.setOrderStatus(orderStatus.getOrderStatus());

        return orderStatusResource;
    }

    @Override
    public List<OrderStatusResource> toOrderStatusResources(List<OrderStatus> orderStatuses){
        if(orderStatuses == null){
            return Collections.emptyList();
        }

        List<OrderStatusResource> orderStatusResources = new ArrayList<>();
        for(OrderStatus orderStatus : orderStatuses){
            orderStatusResources.add(toOrderStatusResource(orderStatus));
        }

        return orderStatusResources;
    }

    @Override
    public OrderStatusResource fromOrderStatusProjection(OrderStatusProjection orderStatusProjection){
        OrderStatusResource orderStatus = new OrderStatusResource();
        orderStatus.setId(orderStatusProjection.getId());
        orderStatus.setOrderStatus(orderStatusProjection.getOrderStatus());
        return orderStatus;
    }
}