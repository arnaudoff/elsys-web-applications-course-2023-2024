package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrdersProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper{
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", source = "orderResource.id")
    @Mapping(target = "client", source = "orderResource.client")
    @Mapping(target = "supportedDeviceType", source = "orderResource.supportedDeviceType")
    @Mapping(target = "model", source = "orderResource.model")
    @Mapping(target = "description", source = "orderResource.description")
    @Mapping(target = "orderStatus", source = "orderResource.orderStatus")
    Order fromOrderResource(OrderResource orderResource);

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "client", source = "order.client")
    @Mapping(target = "supportedDeviceType", source = "order.supportedDeviceType")
    @Mapping(target = "model", source = "order.model")
    @Mapping(target = "description", source = "order.description")
    @Mapping(target = "orderStatus", source = "order.orderStatus")
    OrderResource toOrderResource(Order order);

    OrderResource fromOrderProjection(OrdersProjection orderProjection);

    List<OrderResource> toOrderResources(List<Order> orders);
}