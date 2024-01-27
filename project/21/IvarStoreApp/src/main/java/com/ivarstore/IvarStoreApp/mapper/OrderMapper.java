package com.ivarstore.IvarStoreApp.mapper; 

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.ivarstore.IvarStoreApp.controller.resources.OrderResource;
import com.ivarstore.IvarStoreApp.entity.Order;

@Mapper
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "orderId", source = "orderResource.orderId")
    @Mapping(target = "orderDate", source = "orderResource.orderDate")
    @Mapping(target = "orderTotal", source = "orderResource.orderTotal")
    @Mapping(target = "orderStatus", source = "orderResource.orderStatus")
    @Mapping(target = "customer", source = "orderResource.customer")
    @Mapping(target = "products", source = "orderResource.products")
    Order toEntity(OrderResource orderResource);

    @Mapping(target = "orderId", source = "order.orderId")
    @Mapping(target = "orderDate", source = "order.orderDate")
    @Mapping(target = "orderTotal", source = "order.orderTotal")
    @Mapping(target = "orderStatus", source = "order.orderStatus")
    @Mapping(target = "customer", source = "order.customer")
    @Mapping(target = "products", source = "order.products")
    OrderResource toResource(Order order);

    List<OrderResource> toResources(List<Order> orders);

    @Mapping(target = "orderId", ignore = true)
    void updateEntityFromResource(OrderResource orderResource, @MappingTarget Order order);
}
