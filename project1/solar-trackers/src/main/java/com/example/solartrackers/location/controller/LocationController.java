package com.example.solartrackers.location.controller;

import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.location.dto.UpdateLocationRequest;
import com.example.solartrackers.location.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocation(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @Valid @RequestBody UpdateLocationRequest locationData) {
        return ResponseEntity.ok(locationService.updateLocation(id, locationData));
    }

    @PostMapping("/{id}/solar-trackers/{solarTrackerId}")
    public ResponseEntity<Location> addSolarTracker(@PathVariable Long id, @PathVariable Long solarTrackerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.addSolarTracker(id, solarTrackerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
