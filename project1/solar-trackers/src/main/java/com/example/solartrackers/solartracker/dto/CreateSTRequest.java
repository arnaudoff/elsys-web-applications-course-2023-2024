package com.example.solartrackers.solartracker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Data
public class CreateSTRequest {
    @NotNull
    private final String serialNumber;

    @NotNull
    @Past
    private final Date installationDate;
}
