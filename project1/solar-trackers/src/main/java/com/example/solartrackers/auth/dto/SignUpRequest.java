package com.example.solartrackers.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull
    @Size(min = 3, max = 20)
    private final String username;

    @NotNull
    @Email
    private final String email;

    @NotNull
    private final String password;
}
