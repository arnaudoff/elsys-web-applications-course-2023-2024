package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.elsys_bg.ElectronicsRepair.mapper.OrderStatusMapper;
import org.elsys_bg.ElectronicsRepair.repository.OrderStatusRepository;
import org.elsys_bg.ElectronicsRepair.service.OrderStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService{
    public final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    public OrderStatusResource getById(Long orderStatusId){
        return orderStatusMapper.toOrderStatusResource(orderStatusRepository.getById(orderStatusId));
    }

    public OrderStatusResource getByStatus(String orderStatus){
        return orderStatusMapper.toOrderStatusResource(orderStatusRepository.getByStatus(orderStatus));
    }

    @Override
    public List<OrderStatusResource> findAll(){
        return orderStatusMapper.toOrderStatusResources(orderStatusRepository.findAll());
    }

    @Override
    public OrderStatusResource save(OrderStatus orderStatus){
        return orderStatusMapper.toOrderStatusResource(orderStatusRepository.save(orderStatus));
    }

    @Override
    public void delete(OrderStatus orderStatus){
        orderStatusRepository.delete(orderStatus);
    }

    @Override
    public OrderStatusResource updateOrderStatus(OrderStatus orderStatus) throws NoSuchElementException {
        OrderStatus existingOrderStatus = orderStatusRepository.findById(Long.valueOf(orderStatus.getId())).orElse(null);

        if(existingOrderStatus != null){
            existingOrderStatus.setOrderStatus(orderStatus.getOrderStatus());
            orderStatusRepository.save(existingOrderStatus);
        }else{
            throw new NoSuchElementException("ERR: Order status with ID " + orderStatus.getId() + " does not exist.");
        }

        return orderStatusMapper.toOrderStatusResource(existingOrderStatus);
    }
}