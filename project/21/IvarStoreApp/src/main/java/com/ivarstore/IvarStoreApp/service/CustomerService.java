package com.ivarstore.IvarStoreApp.service;

import java.util.List;

import com.ivarstore.IvarStoreApp.controller.resources.CustomerResource;

public interface CustomerService {

    List<CustomerResource> findAll(); 
    CustomerResource getById(long id); 
    CustomerResource save(CustomerResource CustomerResource);
    CustomerResource update(CustomerResource CustomerResource, long id);
    void delete(long id);
} 
