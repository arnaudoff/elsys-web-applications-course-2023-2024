package com.example.yarnshop.services;

import com.example.yarnshop.entity.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlists();
    Wishlist getWishlistById(Long id);
    Wishlist createWishlist(Wishlist wishlist);
    Wishlist updateWishlist(Long id, Wishlist wishlist);
    void deleteWishlist(Long id);
}
