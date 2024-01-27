package com.example.solartrackers.solartracker.controller;

import com.example.solartrackers.solartracker.entity.SensorsData;
import com.example.solartrackers.solartracker.entity.SolarTracker;
import com.example.solartrackers.solartracker.dto.AddSensorsDataRequest;
import com.example.solartrackers.solartracker.dto.CreateSTRequest;
import com.example.solartrackers.solartracker.service.SolarTrackerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/solar-trackers")
@RequiredArgsConstructor
public class SolarTrackerController {
    private final SolarTrackerService stService;

    @PostMapping("/")
    public ResponseEntity<SolarTracker> createSolarTracker(@Valid @RequestBody CreateSTRequest stData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stService.createSolarTracker(stData));
    }

    @PostMapping("/{serialNumber}/sensors-data")
    public ResponseEntity<SensorsData> addSensorsData(
        @PathVariable String serialNumber, @Valid @RequestBody AddSensorsDataRequest sensorsData
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stService.addSensorsData(serialNumber, sensorsData));
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<SolarTracker> getSolarTracker(@PathVariable String serialNumber) {
        return ResponseEntity.ok(stService.getSolarTracker(serialNumber));
    }

    @GetMapping("/{serialNumber}/sensors-data")
    public ResponseEntity<Set<SensorsData>> getSensorsData(@PathVariable String serialNumber) {
        return ResponseEntity.ok(stService.getSensorsData(serialNumber));
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Void> deleteSolarTracker(@PathVariable String serialNumber) {
        stService.deleteSolarTracker(serialNumber);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{serialNumber}/sensors-data")
    public ResponseEntity<Void> clearSensorsData(@PathVariable String serialNumber) {
        stService.clearSensorsData(serialNumber);
        return ResponseEntity.noContent().build();
    }
}
