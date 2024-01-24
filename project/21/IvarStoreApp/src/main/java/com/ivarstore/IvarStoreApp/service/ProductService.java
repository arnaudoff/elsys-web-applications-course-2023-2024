package com.ivarstore.IvarStoreApp.service;

import com.ivarstore.IvarStoreApp.controller.resources.ProductResource;
import java.util.List;

public interface ProductService {
    List<ProductResource> findAll(); 
    ProductResource getById(long id); 
    ProductResource save(ProductResource productResource);
    ProductResource update(ProductResource productResource, long id);
    void delete(long id);
}
