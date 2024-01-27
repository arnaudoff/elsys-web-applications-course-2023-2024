package com.example.yarnshop.services;

import com.example.yarnshop.entity.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAllInventory();
    Inventory getInventoryById(Long id);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
}
