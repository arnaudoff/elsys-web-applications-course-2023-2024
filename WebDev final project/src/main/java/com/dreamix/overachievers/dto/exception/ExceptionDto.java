package com.dreamix.overachievers.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionDto {
    private LocalDateTime timestamp;
    private String message;
}