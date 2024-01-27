package com.ivarstore.IvarStoreApp.service.impl;

import com.ivarstore.IvarStoreApp.controller.resources.ProductResource;
import com.ivarstore.IvarStoreApp.entity.Product;
import com.ivarstore.IvarStoreApp.mapper.ProductMapper;
import com.ivarstore.IvarStoreApp.repository.ProductRepository;
import com.ivarstore.IvarStoreApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// import static com.ivarstore.IvarStoreApp.mapper.ProductMapper.PRODUCT_MAPPER; 

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResource> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResources(products);
    }

    @Override
    public ProductResource getById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? productMapper.toResource(product) : null;
    }

    @Override
    public ProductResource save(ProductResource productResource) {
        Product product = productMapper.toEntity(productResource);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResource(savedProduct);
    }

    @Override
    public ProductResource update(ProductResource productResource, long id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            productMapper.updateEntityFromResource(productResource, existingProduct);
            Product savedProduct = productRepository.save(existingProduct);
            return productMapper.toResource(savedProduct);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
