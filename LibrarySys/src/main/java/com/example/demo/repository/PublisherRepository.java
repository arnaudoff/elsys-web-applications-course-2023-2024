package com.example.demo.repository;

import com.example.demo.entities.Publisher;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher getPublisherByName(String name);
}

