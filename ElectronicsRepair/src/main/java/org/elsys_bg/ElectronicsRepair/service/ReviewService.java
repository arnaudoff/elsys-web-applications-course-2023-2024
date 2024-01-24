package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.ReviewResource;
import org.elsys_bg.ElectronicsRepair.entity.Review;

import java.util.List;

public interface ReviewService{
    List<ReviewResource> findAll();

    ReviewResource save(Review review);

    void delete(Review review);

    ReviewResource updateReview(Review review);
}