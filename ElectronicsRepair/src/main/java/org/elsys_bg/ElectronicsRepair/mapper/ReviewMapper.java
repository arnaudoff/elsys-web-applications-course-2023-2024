package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.ReviewResource;
import org.elsys_bg.ElectronicsRepair.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper{
    ReviewMapper MAPPER = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "id", source = "reviewResource.id")
    @Mapping(target = "client", source = "reviewResource.client")
    @Mapping(target = "reviewText", source = "reviewResource.reviewText")
    Review fromReviewResource(ReviewResource reviewResource);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "client", source = "review.client")
    @Mapping(target = "reviewText", source = "review.reviewText")
    ReviewResource toReviewResource(Review review);

    List<ReviewResource> toReviewResources(List<Review> reviews);
}