package com.example.restaurant.controller.resources;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderResourceResponse {
    private Long id;
    private ClientResource client;
    private Double totalPrice;
    private List<MealResource> meals;
    private Timestamp date;
}
