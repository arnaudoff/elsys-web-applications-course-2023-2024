package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.mapper.OrderMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrdersProjection;
import org.elsys_bg.ElectronicsRepair.repository.OrderRepository;
import org.elsys_bg.ElectronicsRepair.repository.OrderStatusRepository;
import org.elsys_bg.ElectronicsRepair.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    public final OrderRepository orderRepository;
    public final OrderMapper orderMapper;
    public final OrderStatusRepository orderStatusRepository;

    public OrderResource getById(Long orderId){
        return orderMapper.toOrderResource(orderRepository.getById(orderId));
    }

    public List<OrderResource> getAllOrders(){
        return orderMapper.toOrderResources(orderRepository.getAllOrders());
    }

    public List<OrderResource> getUnfinishedOrders(){
        return orderMapper.toOrderResources(orderRepository.getUnfinishedOrders());
    }


    @Override
    public List<OrderResource> findAll(){
        return orderMapper.toOrderResources(orderRepository.findAll());
    }

    @Override
    public OrderResource save(Order order){
        return orderMapper.toOrderResource(orderRepository.save(order));
    }

    @Override
    public void delete(Order order){
        orderRepository.delete(order);
    }

    @Override
    public OrderResource updateOrder(Order order) throws NoSuchElementException{
        Order existingOrder = orderRepository.findById(Long.valueOf(order.getId())).orElse(null);

        if(existingOrder != null){
            existingOrder.setClient(order.getClient());
            existingOrder.setSupportedDeviceType(order.getSupportedDeviceType());
            existingOrder.setModel(order.getModel());
            existingOrder.setDescription(order.getDescription());
            existingOrder.setOrderStatus(order.getOrderStatus());
            orderRepository.save(existingOrder);
        }else{
            throw new NoSuchElementException("ERR: Order with ID " + order.getId() + " does not exist.");
        }

        return orderMapper.toOrderResource(order);
    }

    public OrderResource finishOrder(Order order){
        OrderStatus status = orderStatusRepository.getByStatus("Finished");
        order.setOrderStatus(status);

        return updateOrder(order);
    }
}