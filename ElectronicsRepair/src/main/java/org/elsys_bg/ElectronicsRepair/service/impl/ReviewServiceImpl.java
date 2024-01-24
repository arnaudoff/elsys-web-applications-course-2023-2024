package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.ReviewResource;
import org.elsys_bg.ElectronicsRepair.entity.Review;
import org.elsys_bg.ElectronicsRepair.mapper.impl.ReviewMapperImpl;
import org.elsys_bg.ElectronicsRepair.repository.ReviewRepository;
import org.elsys_bg.ElectronicsRepair.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    public final ReviewRepository reviewRepository;
    public final ReviewMapperImpl reviewMapper;

    public ReviewResource getById(Long reviewId){
        return reviewMapper.toReviewResource(reviewRepository.getById(reviewId));
    }

    @Override
    public List<ReviewResource> findAll(){
        return reviewMapper.toReviewResources(reviewRepository.findAll());
    }

    @Override
    public ReviewResource save(Review review){
        return reviewMapper.toReviewResource(reviewRepository.save(review));
    }

    @Override
    public void delete(Review review){
        reviewRepository.delete(review);
    }

    @Override
    public ReviewResource updateReview(Review review) throws NoSuchElementException {
        Review existingReview = reviewRepository.findById(Long.valueOf(review.getId())).orElse(null);

        if(existingReview != null){
            existingReview.setClient(review.getClient());
            existingReview.setReviewText(review.getReviewText());
            reviewRepository.save(existingReview);
        }else{
            throw new NoSuchElementException("ERR: Review with ID " + review.getId() + " does not exist.");
        }

        return reviewMapper.toReviewResource(existingReview);
    }
}