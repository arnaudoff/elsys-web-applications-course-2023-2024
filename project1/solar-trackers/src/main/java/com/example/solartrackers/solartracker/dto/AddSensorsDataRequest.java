package com.example.solartrackers.solartracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Data
public class AddSensorsDataRequest {
    @PositiveOrZero
    private final float irradiance;

    @Range(min = 60, max = 120)
    private final float azimuth;

    @Range(min = -120, max = 120)
    private final float azimuthDeviation;

    @Range(min = 0, max = 90)
    private final float elevation;

    @Range(min = -90, max = 90)
    private final float elevationDeviation;

    @NotNull
    @Past
    private final Date timestamp;
}
