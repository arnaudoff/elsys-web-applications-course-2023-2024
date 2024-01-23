package com.example.restaurant.service.impl;

import com.example.restaurant.controller.resources.MealResource;
import com.example.restaurant.entity.Meal;
import com.example.restaurant.repository.MealRepository;
import com.example.restaurant.service.MealService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.restaurant.mapper.MealMapper.MEAL_MAPPER;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    @Override
    public List<MealResource> getAll() {
        return MEAL_MAPPER.toMealResourceList(mealRepository.findAll());
    }

    @Override
    public MealResource getById(Long id) {
        return MEAL_MAPPER.toMealResource(mealRepository.getReferenceById(id));
    }

    @Override
    public MealResource save(MealResource mealResource) {
        Meal meal = MEAL_MAPPER.fromMealResource(mealResource);

        try {
            meal = mealRepository.save(meal);
        } catch (Exception e) {
            throw new EntityExistsException("Meal already exists");
        }

        return MEAL_MAPPER.toMealResource(meal);
    }

    @Override
    public MealResource update(Long id, MealResource mealResource) {
        Meal mealToUpdate = mealRepository.getReferenceById(id);
        mealToUpdate.setName(mealResource.getName());
        mealToUpdate.setDescription(mealResource.getDescription());
        mealToUpdate.setPrice(mealResource.getPrice());

        mealToUpdate = mealRepository.save(mealToUpdate);
        return MEAL_MAPPER.toMealResource(mealToUpdate);
    }

    @Override
    public void delete(Long id) {
        Meal meal = mealRepository.getReferenceById(id);
        mealRepository.deleteById(id);
    }
}
