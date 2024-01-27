package com.example.restaurant.controller;

import com.example.restaurant.controller.resources.MealResource;
import com.example.restaurant.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mealService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mealService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MealResource mealResource) {
        MealResource createdMealResource = mealService.save(mealResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/meals/{id}")
                        .buildAndExpand(createdMealResource.getId())
                        .toUri()
        ).body(createdMealResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MealResource mealResource) {
        MealResource updatedMealResource = mealService.update(id, mealResource);

        return ResponseEntity.ok(updatedMealResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
