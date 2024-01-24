package org.elsys_bg.ElectronicsRepair.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderStatusMapper;
import org.elsys_bg.ElectronicsRepair.mapper.SupportedDeviceForRepairMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.ClientProjection;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrdersProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper{
    private final ClientMapper clientMapper;
    private final SupportedDeviceForRepairMapper supportedDeviceMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public Order fromOrderResource(OrderResource orderResource){
        if(orderResource == null){
            return null;
        }

        Order order = new Order();
        order.setId(orderResource.getId());
        order.setClient(clientMapper.fromClientResource(orderResource.getClient()));
        order.setSupportedDeviceType(supportedDeviceMapper.fromSupportedDeviceForRepairResource(orderResource.getSupportedDeviceType()));
        order.setModel(orderResource.getModel());
        order.setDescription(orderResource.getDescription());
        order.setOrderStatus(orderStatusMapper.fromOrderStatusResource(orderResource.getOrderStatus()));
        return order;
    }

    @Override
    public OrderResource toOrderResource(Order order){
        if(order == null){
            return null;
        }

        OrderResource orderResource = new OrderResource();
        orderResource.setId(order.getId());
        orderResource.setClient(clientMapper.toClientResource(order.getClient()));
        orderResource.setSupportedDeviceType(supportedDeviceMapper.toSupportedDeviceForRepairResource(order.getSupportedDeviceType()));
        orderResource.setModel(order.getModel());
        orderResource.setDescription(order.getDescription());
        orderResource.setOrderStatus(orderStatusMapper.toOrderStatusResource(order.getOrderStatus()));
        return orderResource;
    }

    @Override
    public OrderResource fromOrderProjection(OrdersProjection orderProjection){
        if(orderProjection == null){
            return null;
        }

        OrderResource orderResource = new OrderResource();
        orderResource.setId(orderProjection.getId());
        orderResource.setClient(clientMapper.fromClientProjection(orderProjection.getClient()));
        orderResource.setSupportedDeviceType(supportedDeviceMapper.fromSupportedDeviceForRepairProjection(orderProjection.getSupportedDeviceType()));
        orderResource.setModel(orderProjection.getModel());
        orderResource.setDescription(orderProjection.getDescription());
        orderResource.setOrderStatus(orderStatusMapper.fromOrderStatusProjection(orderProjection.getOrderStatus()));
        return orderResource;
    }

    @Override
    public List<OrderResource> toOrderResources(List<Order> orders){
        if(orders == null){
            return null;
        }

        return orders.stream()
                .map(this::toOrderResource)
                .collect(Collectors.toList());
    }
}