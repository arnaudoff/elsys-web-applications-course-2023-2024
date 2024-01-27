package com.ivarstore.IvarStoreApp.controller.resources;

import lombok.Data;

@Data
public class ProductResource {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int quantityInStock;
}
