package com.ivarstore.IvarStoreApp.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long orderId;

    @Temporal(TemporalType.TIMESTAMP) 
    private Date orderDate; 

    private BigDecimal orderTotal; 
    private String orderStatus; 

    @ManyToOne
    @JoinColumn(name = "customer_id") 
    private Customer customer; 

    @ManyToMany
    @JoinTable(name = "order_product", 
        joinColumns = @JoinColumn(name = "order_id"), 
        inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products; 
}
