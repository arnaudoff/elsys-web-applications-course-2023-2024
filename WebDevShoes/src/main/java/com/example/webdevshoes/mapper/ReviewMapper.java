package com.example.webdevshoes.mapper;

import com.example.webdevshoes.DTO.ReviewDTO;
import com.example.webdevshoes.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewMapper REVIEW_MAPPER = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "user.username", source = "reviewDTO.user")
    @Mapping(target = "shoe.model", source = "reviewDTO.shoe")
    Review fromReviewDTO(ReviewDTO reviewDTO);

    @Mapping(target = "user", source = "review.user.username")
    @Mapping(target = "shoe", source = "review.shoe.model")
    ReviewDTO toReviewDTO(Review review);

    List<ReviewDTO> toReviewDTOs(List<Review> reviews);
}
