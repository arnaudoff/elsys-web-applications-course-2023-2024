package com.ivarstore.IvarStoreApp.controller.resources;

import lombok.Data;

@Data
public class CustomerResource {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String zipCode;
}
