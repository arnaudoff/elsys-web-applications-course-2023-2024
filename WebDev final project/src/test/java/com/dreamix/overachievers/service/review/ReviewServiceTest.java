package com.dreamix.overachievers.service.review;

import com.dreamix.overachievers.dto.review.ReviewDto;
import com.dreamix.overachievers.entity.Review;
import com.dreamix.overachievers.exception.EntityAlreadyExistsException;
import com.dreamix.overachievers.mapper.ReviewMapper;
import com.dreamix.overachievers.repository.ReviewRepository;
import com.dreamix.overachievers.service.review.impl.ReviewServiceImpl;
import com.dreamix.overachievers.vo.ReviewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReviewsForEmployeeTest() {

        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewRepository.findAllByReceiverIdAndType(1L, ReviewType.FEEDBACK)).thenReturn(reviews);

        reviewService.getAllReviewsForEmployee(1L, ReviewType.FEEDBACK);
        verify(reviewRepository).findAllByReceiverIdAndType(1L, ReviewType.FEEDBACK);

    }

    @Test
    void createReviewTest_WhenVideoUrlIsUnique_ThenSuccess() {

        ReviewDto reviewDto = new ReviewDto();
        when(reviewRepository.findByVideoUrl(reviewDto.getVideoUrl())).thenReturn(Optional.empty());

        Review review = new Review();
        when(reviewMapper.reviewDtoToReviewEntity(reviewDto)).thenReturn(review);

        ResponseEntity<String> result = reviewService.createReview(reviewDto);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Created review!", result.getBody());

        verify(reviewRepository).save(review);
        verify(reviewRepository).findByVideoUrl(reviewDto.getVideoUrl());

    }

    @Test
    void createReviewTest_WhenVideoUrlIsDuplicate_ThenRespondBadRequest() {

        ReviewDto reviewDto = new ReviewDto();
        when(reviewRepository.findByVideoUrl(reviewDto.getVideoUrl())).thenReturn(Optional.of(new Review()));

        assertThrows(EntityAlreadyExistsException.class, () -> reviewService.createReview(reviewDto));
    }
}