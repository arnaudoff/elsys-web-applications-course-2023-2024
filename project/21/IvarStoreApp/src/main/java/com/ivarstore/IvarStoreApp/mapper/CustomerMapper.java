package com.ivarstore.IvarStoreApp.mapper; 

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.ivarstore.IvarStoreApp.controller.resources.CustomerResource;
import com.ivarstore.IvarStoreApp.entity.Customer;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerId", source = "customerResource.customerId")
    @Mapping(target = "firstName", source = "customerResource.firstName")
    @Mapping(target = "lastName", source = "customerResource.lastName")
    @Mapping(target = "email", source = "customerResource.email")
    @Mapping(target = "password", source = "customerResource.password")
    @Mapping(target = "phoneNumber", source = "customerResource.phoneNumber")
    @Mapping(target = "address", source = "customerResource.address")
    @Mapping(target = "city", source = "customerResource.city")
    @Mapping(target = "zipCode", source = "customerResource.zipCode")
    Customer toEntity(CustomerResource customerResource);

    @Mapping(target = "customerId", source = "customer.customerId")
    @Mapping(target = "firstName", source = "customer.firstName")
    @Mapping(target = "lastName", source = "customer.lastName")
    @Mapping(target = "email", source = "customer.email")
    @Mapping(target = "password", source = "customer.password")
    @Mapping(target = "phoneNumber", source = "customer.phoneNumber")
    @Mapping(target = "address", source = "customer.address")
    @Mapping(target = "city", source = "customer.city")
    @Mapping(target = "zipCode", source = "customer.zipCode")
    CustomerResource toResource(Customer customer);

    List<CustomerResource> toResources(List<Customer> customers);

    @Mapping(target = "customerId", ignore = true)
    void updateEntityFromResource(CustomerResource customerResource, @MappingTarget Customer customer);
}
