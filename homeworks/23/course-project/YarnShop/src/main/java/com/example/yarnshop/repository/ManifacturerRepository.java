package com.example.yarnshop.repository;

import com.example.yarnshop.entity.Manifacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManifacturerRepository extends JpaRepository<Manifacturer, Long> {
}
