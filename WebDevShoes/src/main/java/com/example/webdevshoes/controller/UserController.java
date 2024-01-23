package com.example.webdevshoes.controller;

import com.example.webdevshoes.DTO.UserDTO;
import com.example.webdevshoes.DTO.UserRequestDTO;
import com.example.webdevshoes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> save(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
