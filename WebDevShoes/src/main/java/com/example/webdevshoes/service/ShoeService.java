package com.example.webdevshoes.service;

import com.example.webdevshoes.DTO.ShoeDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ShoeService {
    List<ShoeDTO> getAll();
    ShoeDTO getById(Long id);
    ShoeDTO save(ShoeDTO shoe);
    ShoeDTO update(ShoeDTO shoe, Long id);
    void delete(Long id);
    List<ShoeDTO> getByBrand(String brand);
    List<ShoeDTO> getBySize(String size);
    List<ShoeDTO> getByPriceLessThan(BigDecimal price);
}
