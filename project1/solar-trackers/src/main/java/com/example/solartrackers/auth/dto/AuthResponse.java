package com.example.solartrackers.auth.dto;

import com.example.solartrackers.user.entity.User;
import lombok.Data;

@Data
public class AuthResponse {
    private final User user;
    private final String token;
}
