package com.example.restaurant.controller.resources;

import lombok.Data;

@Data
public class ClientResource {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
