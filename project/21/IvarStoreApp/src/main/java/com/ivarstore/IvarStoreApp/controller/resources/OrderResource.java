package com.ivarstore.IvarStoreApp.controller.resources;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ivarstore.IvarStoreApp.entity.Customer;
import com.ivarstore.IvarStoreApp.entity.Product;

import lombok.Data;

@Data
public class OrderResource {
    private Long orderId;
    private Date orderDate; 
    private BigDecimal orderTotal; 
    private String orderStatus; 
    private Customer customer; 
    private List<Product> products; 
}
