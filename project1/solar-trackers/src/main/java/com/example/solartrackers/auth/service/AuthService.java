package com.example.solartrackers.auth.service;

import com.example.solartrackers.auth.dto.AuthResponse;
import com.example.solartrackers.auth.dto.SignInRequest;
import com.example.solartrackers.auth.dto.SignUpRequest;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpData);
    AuthResponse signIn(SignInRequest signInData);
}
