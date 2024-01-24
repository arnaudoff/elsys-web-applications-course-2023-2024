package org.elsys_bg.ElectronicsRepair.repository;

import jakarta.transaction.Transactional;
import org.elsys_bg.ElectronicsRepair.entity.WorkerPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerPostRepository extends JpaRepository<WorkerPost, Long>{
    @Query("SELECT wp FROM WorkerPost wp WHERE wp.post = :post")
    WorkerPost getByPost(@Param("post") String post);
}