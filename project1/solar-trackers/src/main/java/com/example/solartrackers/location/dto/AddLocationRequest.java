package com.example.solartrackers.location.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Set;

@Data
public class AddLocationRequest {
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;

    @Range(min = -90, max = 90)
    private final float latitude;

    @Range(min = -180, max = 180)
    private final float longitude;

    @NotNull
    private final Set<String> solarTrackers;
}
