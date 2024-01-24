package com.example.solartrackers.auth.controller;

import com.example.solartrackers.auth.dto.AuthResponse;
import com.example.solartrackers.auth.dto.SignInRequest;
import com.example.solartrackers.auth.dto.SignUpRequest;
import com.example.solartrackers.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody SignUpRequest signUpData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpData));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest signInData) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(signInData));
    }
}
