package com.dreamix.overachievers.repository;

import com.dreamix.overachievers.entity.Review;
import com.dreamix.overachievers.vo.ReviewType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByReceiverIdAndType(Long receiverId, ReviewType type);
    Optional<Review> findByVideoUrl(String videoUrl);
    List<Review> findAllByReceiverId(Long receiverId);
}