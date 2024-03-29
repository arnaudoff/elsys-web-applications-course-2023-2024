package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.ActivityStatResource;
import com.example.fitnessapp1.service.ActivityStatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/activity-stat")
@RequiredArgsConstructor
public class ActivityStatController {
    private final ActivityStatService activityStatService;

    @PostMapping("/create/{id}")
    private ResponseEntity<ActivityStatResource> createActivityStat(@Valid @RequestBody ActivityStatResource activityStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(activityStatService.create(activityStatResource, id));
    }

    // TODO: think about this
    @GetMapping("/search")
    private ResponseEntity<?> getAllByDate(@RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(activityStatService.searchAllByDate(date));
    }

    @PatchMapping("/{userId}/{id}")
    private ResponseEntity<ActivityStatResource> updateActivityStat(@Valid @RequestBody ActivityStatResource activityStatResource, @PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        return ResponseEntity.ok(activityStatService.update(activityStatResource, userId, id));
    }
}
