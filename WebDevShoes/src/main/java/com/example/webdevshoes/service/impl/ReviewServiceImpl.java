package com.example.webdevshoes.service.impl;

import com.example.webdevshoes.DTO.ReviewDTO;
import com.example.webdevshoes.entity.Review;
import com.example.webdevshoes.repository.ReviewRepository;
import com.example.webdevshoes.repository.ShoeRepository;
import com.example.webdevshoes.repository.UserRepository;
import com.example.webdevshoes.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.webdevshoes.mapper.ReviewMapper.REVIEW_MAPPER;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ShoeRepository shoeRepository;

    @Override
    public List<ReviewDTO> getAll() {
        return REVIEW_MAPPER.toReviewDTOs(reviewRepository.findAll());
    }

    @Override
    public ReviewDTO getById(Long id) {
        return REVIEW_MAPPER.toReviewDTO(reviewRepository.getReferenceById(id));
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review reviewToSave = REVIEW_MAPPER.fromReviewDTO(reviewDTO);

        userRepository.findByUsername(reviewDTO.getUser()).ifPresentOrElse(
                reviewToSave::setUser,
                () -> {
                    throw new EntityNotFoundException("User not found");
                }
        );

        shoeRepository.findByModel(reviewDTO.getShoe()).ifPresentOrElse(
                reviewToSave::setShoe,
                () -> {
                    throw new EntityNotFoundException("Shoe not found");
                }
        );

        if (reviewToSave.getShoe().getUsers().contains(reviewToSave.getUser())) {
            throw new DuplicateKeyException("User already reviewed this shoe");
        }

        reviewToSave.getShoe().getUsers().add(reviewToSave.getUser());
        reviewToSave.getUser().getShoes().add(reviewToSave.getShoe());
        shoeRepository.save(reviewToSave.getShoe());
        userRepository.save(reviewToSave.getUser());

        return REVIEW_MAPPER.toReviewDTO(reviewRepository.save(reviewToSave));
    }

    @Override
    public ReviewDTO update(ReviewDTO reviewDTO, Long id) {
        Review reviewToUpdate = reviewRepository.getReferenceById(id);

        reviewToUpdate.setComment(reviewDTO.getComment());
        reviewToUpdate.setRating(reviewDTO.getRating());

        return REVIEW_MAPPER.toReviewDTO(reviewRepository.save(reviewToUpdate));
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> getAllByRating(Integer rating) {
        return REVIEW_MAPPER.toReviewDTOs(reviewRepository.findAllByRating(rating));
    }

    @Override
    public List<ReviewDTO> getAllByUser(String username) {
        return REVIEW_MAPPER.toReviewDTOs(
                reviewRepository.findAllByUserId(
                        userRepository.findByUsername(username).orElseThrow(
                                () -> new EntityNotFoundException("User not found")
                        ).getId()
                )
        );
    }

    @Override
    public List<ReviewDTO> getAllByShoe(String model) {
        return REVIEW_MAPPER.toReviewDTOs(
                reviewRepository.findAllByShoeId(
                        shoeRepository.findByModel(model).orElseThrow(
                                () -> new EntityNotFoundException("Shoe not found")
                        ).getId()
                )
        );
    }
}
