package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrderStatusProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderStatusMapper{
    OrderStatusMapper MAPPER = Mappers.getMapper(OrderStatusMapper.class);

    @Mapping(target = "id", source = "orderStatusResource.id")
    @Mapping(target = "orderStatus", source = "orderStatusResource.orderStatus")
    OrderStatus fromOrderStatusResource(OrderStatusResource orderStatusResource);

    @Mapping(target = "id", source = "orderStatus.id")
    @Mapping(target = "orderStatus", source = "orderStatus.orderStatus")
    OrderStatusResource toOrderStatusResource(OrderStatus orderStatus);

    List<OrderStatusResource> toOrderStatusResources(List<OrderStatus> orderStatuses);

    OrderStatusResource fromOrderStatusProjection(OrderStatusProjection orderStatusProjection);
}
