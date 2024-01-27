package com.example.restaurant.service.impl;

import com.example.restaurant.controller.resources.OrderResourceRequest;
import com.example.restaurant.controller.resources.OrderResourceResponse;
import com.example.restaurant.entity.Client;
import com.example.restaurant.entity.Meal;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.ClientRepository;
import com.example.restaurant.repository.MealRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.service.OrderService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.example.restaurant.mapper.OrderMapper.ORDER_MAPPER;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final MealRepository mealRepository;

    @Override
    public List<OrderResourceResponse> getAll() {
        return ORDER_MAPPER.toOrderResourceList(orderRepository.findAll());
    }

    @Override
    public OrderResourceResponse getById(Long id) {
        return ORDER_MAPPER.toOrderResource(orderRepository.getReferenceById(id));
    }

    @Override
    public OrderResourceResponse save(OrderResourceRequest orderResource) {
        Order order = ORDER_MAPPER.fromOrderResource(orderResource);
        System.out.println(orderResource);
        System.out.println(order);
        Client client = clientRepository.findByName(orderResource.getClient()).orElseThrow(
                () -> new EntityExistsException("Client does not exist")
        );
        List<Meal> meals = orderResource.getMeals().stream().map(
                mealName -> mealRepository.findByName(mealName).orElseThrow(
                        () -> new EntityExistsException("Meal does not exist")
                )
        ).toList();

        order.setClient(client);
        order.setMeals(meals);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalPrice(meals.stream().mapToDouble(Meal::getPrice).sum());

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new EntityExistsException("Order already exists");
        }

        return ORDER_MAPPER.toOrderResource(order);
    }

    @Override
    public OrderResourceResponse update(Long id, OrderResourceRequest orderResource) {
        Order orderToUpdate = orderRepository.getReferenceById(id);
        Client client = clientRepository.findByName(orderResource.getClient()).orElseThrow(
                () -> new EntityExistsException("Client does not exist")
        );
        List<Meal> meals = orderResource.getMeals().stream().map(
                mealName -> mealRepository.findByName(mealName).orElseThrow(
                        () -> new EntityExistsException("Meal does not exist")
                )
        ).toList();

        orderToUpdate.setClient(client);
        orderToUpdate.setMeals(meals);

        orderToUpdate = orderRepository.save(orderToUpdate);
        return ORDER_MAPPER.toOrderResource(orderToUpdate);
    }

    @Override
    public void delete(Long id) {
        Order order = orderRepository.getReferenceById(id);
        orderRepository.deleteById(id);
    }
}
