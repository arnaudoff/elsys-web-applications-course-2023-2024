package com.ivarstore.IvarStoreApp.service.impl;

import com.ivarstore.IvarStoreApp.controller.resources.CustomerResource;
import com.ivarstore.IvarStoreApp.entity.Customer;
import com.ivarstore.IvarStoreApp.mapper.CustomerMapper;
import com.ivarstore.IvarStoreApp.repository.CustomerRepository;
import com.ivarstore.IvarStoreApp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerResource> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toResources(customers);
    }

    @Override
    public CustomerResource getById(long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customer != null ? customerMapper.toResource(customer) : null;
    }

    @Override
    public CustomerResource save(CustomerResource customerResource) {
        Customer customer = customerMapper.toEntity(customerResource);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toResource(savedCustomer);
    }

    @Override
    public CustomerResource update(CustomerResource customerResource, long id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            customerMapper.updateEntityFromResource(customerResource, existingCustomer);
            Customer savedCustomer = customerRepository.save(existingCustomer);
            return customerMapper.toResource(savedCustomer);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
