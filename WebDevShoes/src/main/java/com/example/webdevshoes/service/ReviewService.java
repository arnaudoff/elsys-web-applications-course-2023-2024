package com.example.webdevshoes.service;

import com.example.webdevshoes.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAll();
    ReviewDTO getById(Long id);
    ReviewDTO save(ReviewDTO review);
    ReviewDTO update(ReviewDTO review, Long id);
    void delete(Long id);
    List<ReviewDTO> getAllByRating(Integer rating);
    List<ReviewDTO> getAllByUser(String username);
    List<ReviewDTO> getAllByShoe(String model);
}
