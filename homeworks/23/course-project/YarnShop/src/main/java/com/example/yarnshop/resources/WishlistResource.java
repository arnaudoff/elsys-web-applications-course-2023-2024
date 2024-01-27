package com.example.yarnshop.resources;

import com.example.yarnshop.entity.Wishlist;
import com.example.yarnshop.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlists")
public class WishlistResource {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistResource(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    @GetMapping("/{id}")
    public Wishlist getWishlistById(@PathVariable Long id) {
        return wishlistService.getWishlistById(id);
    }

    @PostMapping
    public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.createWishlist(wishlist);
    }

    @PutMapping("/{id}")
    public Wishlist updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        return wishlistService.updateWishlist(id, wishlist);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
    }
}
