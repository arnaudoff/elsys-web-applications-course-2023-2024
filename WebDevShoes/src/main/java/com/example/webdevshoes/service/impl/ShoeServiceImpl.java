package com.example.webdevshoes.service.impl;

import com.example.webdevshoes.DTO.ShoeDTO;
import com.example.webdevshoes.entity.Review;
import com.example.webdevshoes.entity.Shoe;
import com.example.webdevshoes.repository.ReviewRepository;
import com.example.webdevshoes.repository.ShoeRepository;
import com.example.webdevshoes.repository.UserRepository;
import com.example.webdevshoes.service.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.webdevshoes.mapper.ShoeMapper.SHOE_MAPPER;

@Service
@RequiredArgsConstructor
public class ShoeServiceImpl implements ShoeService {
    private final ShoeRepository shoeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<ShoeDTO> getAll() {
        return SHOE_MAPPER.toShoeDTOs(shoeRepository.findAll());
    }

    @Override
    public ShoeDTO getById(Long id) {
        return SHOE_MAPPER.toShoeDTO(shoeRepository.getReferenceById(id));
    }

    @Override
    public ShoeDTO save(ShoeDTO shoeDTO) {
        if (shoeRepository.existsByModel(shoeDTO.getModel())) {
            throw new DuplicateKeyException("Shoe already exists");
        }

        Shoe shoeToSave = shoeRepository.save(SHOE_MAPPER.fromShoeDTO(shoeDTO));
        shoeToSave.setReviews(null);
        return SHOE_MAPPER.toShoeDTO(shoeToSave);
    }

    @Override
    public ShoeDTO update(ShoeDTO shoeDTO, Long id) {
        Shoe shoeToUpdate = shoeRepository.getReferenceById(id);

        shoeToUpdate.setModel(shoeDTO.getModel());
        shoeToUpdate.setBrand(shoeDTO.getBrand());
        shoeToUpdate.setColor(shoeDTO.getColor());
        shoeToUpdate.setSize(shoeDTO.getSize());
        shoeToUpdate.setPrice(shoeDTO.getPrice());

        return SHOE_MAPPER.toShoeDTO(shoeRepository.save(shoeToUpdate));
    }

    @Override
    public void delete(Long id) {
        List<Review> reviewToDelete = reviewRepository.findAllByShoeId(id);
        reviewRepository.deleteAll(reviewToDelete);

        removeShoeFromUser(id);

        shoeRepository.deleteById(id);
    }

    private void removeShoeFromUser(Long id) {
        userRepository.findAllByShoesId(id).forEach(user -> {
            user.getShoes().removeIf(shoe -> shoe.getId().equals(id));
            userRepository.save(user);
        });
    }

    @Override
    public List<ShoeDTO> getByBrand(String brand) {
        return SHOE_MAPPER.toShoeDTOs(shoeRepository.findAllByBrand(brand));
    }

    @Override
    public List<ShoeDTO> getBySize(String size) {
        return SHOE_MAPPER.toShoeDTOs(shoeRepository.findAllBySize(size));
    }

    @Override
    public List<ShoeDTO> getByPriceLessThan(BigDecimal price) {
        return SHOE_MAPPER.toShoeDTOs(shoeRepository.findAllByPriceLessThan(price));
    }
}
