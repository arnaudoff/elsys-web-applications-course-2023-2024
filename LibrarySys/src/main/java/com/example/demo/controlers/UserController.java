package com.example.demo.controlers;

import com.example.demo.controlers.resources.UserRes;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(service.AllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRes user) {
        UserRes saved = service.save(user);
        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(saved.getId())
                .toUri()).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRes user, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
