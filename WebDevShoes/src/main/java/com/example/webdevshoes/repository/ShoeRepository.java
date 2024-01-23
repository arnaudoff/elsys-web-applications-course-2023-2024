package com.example.webdevshoes.repository;

import com.example.webdevshoes.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    Optional<Shoe> findByModel(String model);
    List<Shoe> findAllByBrand(String brand);
    List<Shoe> findAllBySize(String size);
    List<Shoe> findAllByPriceLessThan(BigDecimal price);
    List<Shoe> findAllByUsersId(Long id);
    Boolean existsByModel(String model);
}
