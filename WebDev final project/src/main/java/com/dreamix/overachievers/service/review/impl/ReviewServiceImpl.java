package com.dreamix.overachievers.service.review.impl;

import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.entity.Review;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.ReviewMapper;
import com.dreamix.overachievers.repository.ReviewRepository;
import com.dreamix.overachievers.service.review.ReviewService;
import com.dreamix.overachievers.vo.ReviewType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> getAllReviewsForEmployee(Long receiverId, ReviewType type) {
        return reviewMapper.reviewEntityToReviewDto(
                reviewRepository.findAllByReceiverIdAndType(
                        receiverId, type));
    }

    @Override
    public ResponseEntity<String> createReview(ReviewDto reviewDto) {
        Optional<Review> reviewOptional = reviewRepository.findByVideoUrl(reviewDto.getVideoUrl());
        if (reviewOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Review with this video URL: "
                    + reviewDto.getVideoUrl()+ " already exists!");
        }

        reviewRepository.save(
                reviewMapper.reviewDtoToReviewEntity(reviewDto));

        return new ResponseEntity<>( "Created review!", HttpStatus.OK);
    }
}