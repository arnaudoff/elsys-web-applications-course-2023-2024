package com.example.solartrackers.user.controller;

import com.example.solartrackers.location.dto.AddLocationRequest;
import com.example.solartrackers.location.entity.Location;
import com.example.solartrackers.user.dto.UpdateUserRequest;
import com.example.solartrackers.user.entity.User;
import com.example.solartrackers.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest userData) {
        return ResponseEntity.ok(userService.updateUser(id, userData));
    }

    @PostMapping("/{id}/locations")
    public ResponseEntity<Location> addNewLocation(@PathVariable Long id, @Valid @RequestBody AddLocationRequest locationData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewLocation(id, locationData));
    }

    @PostMapping("/{id}/locations/{locationId}")
    public ResponseEntity<Location> addExistingLocation(@PathVariable Long id, @PathVariable Long locationId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addExistingLocation(id, locationId));
    }

    @GetMapping("/{id}/locations")
    public ResponseEntity<Set<Location>> getLocations(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getLocations(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
