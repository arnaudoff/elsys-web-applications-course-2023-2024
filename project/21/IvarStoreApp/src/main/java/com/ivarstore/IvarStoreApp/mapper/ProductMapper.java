package com.ivarstore.IvarStoreApp.mapper; 

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.ivarstore.IvarStoreApp.controller.resources.ProductResource;
import com.ivarstore.IvarStoreApp.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productId", source = "productResource.productId")
    @Mapping(target = "productName", source = "productResource.productName")
    @Mapping(target = "description", source = "productResource.description")
    @Mapping(target = "price", source = "productResource.price")
    @Mapping(target = "quantityInStock", source = "productResource.quantityInStock")
    Product toEntity(ProductResource productResource);

    @Mapping(target = "productId", source = "product.productId")
    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "quantityInStock", source = "product.quantityInStock")
    ProductResource toResource(Product product);

    List<ProductResource> toResources(List<Product> products);

    @Mapping(target = "productId", ignore = true)
    void updateEntityFromResource(ProductResource productResource, @MappingTarget Product product);
}
