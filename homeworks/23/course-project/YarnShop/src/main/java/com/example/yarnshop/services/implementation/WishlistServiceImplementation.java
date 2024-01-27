package com.example.yarnshop.services.implementation;

import com.example.yarnshop.entity.Wishlist;
import com.example.yarnshop.repository.WishlistRepository;
import com.example.yarnshop.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImplementation implements WishlistService {
    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistServiceImplementation(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    @Override
    public Wishlist getWishlistById(Long id) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        return optionalWishlist.orElse(null);
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist updateWishlist(Long id, Wishlist wishlist) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if (optionalWishlist.isPresent()) {
            Wishlist existingWishlist = optionalWishlist.get();
            existingWishlist.setName(wishlist.getName());
            return wishlistRepository.save(existingWishlist);
        }
        return null;
    }

    @Override
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
