package com.example.restaurant.mapper;

import com.example.restaurant.controller.resources.MealResource;
import com.example.restaurant.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MealMapper {
    MealMapper MEAL_MAPPER = Mappers.getMapper(MealMapper.class);
    Meal fromMealResource(MealResource mealResource);
    MealResource toMealResource(Meal meal);
    List<MealResource> toMealResourceList(List<Meal> meals);
    List<Meal> fromMealResourceList(List<MealResource> mealResources);

    @Mapping(target = "name", source = "meal")
    Meal fromString(String meal);

    List<Meal> fromStringList(List<String> meals);
}
