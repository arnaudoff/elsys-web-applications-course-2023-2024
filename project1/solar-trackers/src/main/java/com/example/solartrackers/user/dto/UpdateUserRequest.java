package com.example.solartrackers.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull
    @Size(min = 3, max = 20)
    private String username;
}
