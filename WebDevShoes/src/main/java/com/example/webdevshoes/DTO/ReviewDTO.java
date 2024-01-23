package com.example.webdevshoes.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String comment;
    private int rating;
    private String user;
    private String shoe;
}
