package com.example.webdevshoes.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoeDTO {
    private Long id;
    private String model;
    private String brand;
    private String color;
    private String size;
    private BigDecimal price;
    private List<ReviewDTO> reviews;
}
