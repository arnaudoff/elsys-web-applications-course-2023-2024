package com.example.restaurant.mapper;

import com.example.restaurant.controller.resources.OrderResourceRequest;
import com.example.restaurant.controller.resources.OrderResourceResponse;
import com.example.restaurant.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MealMapper.class, ClientMapper.class})
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "client.name", source = "orderResource.client")
    @Mapping(target = "meals", source = "orderResource.meals")
    Order fromOrderResource(OrderResourceRequest orderResource);

    @Mapping(target = "date", source = "order.date")
    OrderResourceResponse toOrderResource(Order order);

    List<OrderResourceResponse> toOrderResourceList(List<Order> orders);
}
