package com.example.solartrackers.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInRequest {
    @NotNull
    @Email
    private final String email;

    @NotNull
    private final String password;
}
