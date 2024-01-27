package com.example.yarnshop.repository;

import com.example.yarnshop.entity.Yarn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YarnRepository extends JpaRepository<Yarn, Long> {
}
