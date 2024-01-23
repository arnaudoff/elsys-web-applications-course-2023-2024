package com.example.webdevshoes.repository;

import com.example.webdevshoes.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserId(Long userId);
    List<Review> findAllByShoeId(Long shoeId);
    List<Review> findAllByRating(Integer rating);
}
