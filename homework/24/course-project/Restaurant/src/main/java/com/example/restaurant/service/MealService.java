package com.example.restaurant.service;

import com.example.restaurant.controller.resources.MealResource;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MealService {
    List<MealResource> getAll();
    MealResource getById(Long id);
    MealResource save(MealResource mealResource);
    MealResource update(Long id, MealResource mealResource);
    void delete(Long id);
}
