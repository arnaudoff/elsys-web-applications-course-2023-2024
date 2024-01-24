package com.example.restaurant.mapper;

import com.example.restaurant.controller.resources.MealResource;
import com.example.restaurant.entity.Meal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T00:00:24+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class MealMapperImpl implements MealMapper {

    @Override
    public Meal fromMealResource(MealResource mealResource) {
        if ( mealResource == null ) {
            return null;
        }

        Meal meal = new Meal();

        meal.setId( mealResource.getId() );
        meal.setName( mealResource.getName() );
        meal.setDescription( mealResource.getDescription() );
        meal.setPrice( mealResource.getPrice() );

        return meal;
    }

    @Override
    public MealResource toMealResource(Meal meal) {
        if ( meal == null ) {
            return null;
        }

        MealResource mealResource = new MealResource();

        mealResource.setId( meal.getId() );
        mealResource.setName( meal.getName() );
        mealResource.setDescription( meal.getDescription() );
        mealResource.setPrice( meal.getPrice() );

        return mealResource;
    }

    @Override
    public List<MealResource> toMealResourceList(List<Meal> meals) {
        if ( meals == null ) {
            return null;
        }

        List<MealResource> list = new ArrayList<MealResource>( meals.size() );
        for ( Meal meal : meals ) {
            list.add( toMealResource( meal ) );
        }

        return list;
    }

    @Override
    public List<Meal> fromMealResourceList(List<MealResource> mealResources) {
        if ( mealResources == null ) {
            return null;
        }

        List<Meal> list = new ArrayList<Meal>( mealResources.size() );
        for ( MealResource mealResource : mealResources ) {
            list.add( fromMealResource( mealResource ) );
        }

        return list;
    }

    @Override
    public Meal fromString(String meal) {
        if ( meal == null ) {
            return null;
        }

        Meal meal1 = new Meal();

        meal1.setName( meal );

        return meal1;
    }

    @Override
    public List<Meal> fromStringList(List<String> meals) {
        if ( meals == null ) {
            return null;
        }

        List<Meal> list = new ArrayList<Meal>( meals.size() );
        for ( String string : meals ) {
            list.add( fromString( string ) );
        }

        return list;
    }
}
