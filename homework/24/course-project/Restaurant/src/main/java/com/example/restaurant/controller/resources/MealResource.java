package com.example.restaurant.controller.resources;

import lombok.Data;

@Data
public class MealResource {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
