package com.dreamix.overachievers.repository;

import com.dreamix.overachievers.entity.DailyUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DailyUpdateRepository extends JpaRepository<DailyUpdate, Long> {
    @Query("SELECT du FROM daily_update du WHERE du.sender.id = :senderId " +
            "AND (du.createdAt = TO_TIMESTAMP(:start, 'YYYY-MM-DD hh24:mi:ss') " +
            "OR du.createdAt = TO_TIMESTAMP(:end, 'YYYY-MM-DD hh24:mi:ss') " +
            "OR (du.createdAt BETWEEN TO_TIMESTAMP(:start, 'YYYY-MM-DD hh24:mi:ss') " +
            "AND TO_TIMESTAMP(:end, 'YYYY-MM-DD hh24:mi:ss')))")
    Optional<DailyUpdate> findBySenderIdAndCreatedAt(@Param("senderId") Long senderId,
                                                     @Param("start") LocalDateTime start,
                                                     @Param("end") LocalDateTime end);
}