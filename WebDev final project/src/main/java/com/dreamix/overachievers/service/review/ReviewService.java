package com.dreamix.overachievers.service.review;

import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.vo.ReviewType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviewsForEmployee(Long receiverId, ReviewType type);
    ResponseEntity<String> createReview(ReviewDto reviewDto);
}