package com.example.webdevshoes.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private List<ReviewDTO> reviews;
    private List<ShoeDTO> shoes;
}
