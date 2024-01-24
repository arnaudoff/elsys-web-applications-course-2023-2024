package com.example.restaurant.controller.resources;

import lombok.Data;

import java.util.List;

@Data
public class OrderResourceRequest {
    private Long id;
    private String client;
    private List<String> meals;
}
