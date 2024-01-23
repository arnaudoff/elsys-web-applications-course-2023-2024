package com.example.restaurant.service;

import com.example.restaurant.controller.resources.ClientResource;
import com.example.restaurant.entity.Client;

import java.util.List;

public interface ClientService {
    List<ClientResource> getAll();
    ClientResource getById(Long id);
    ClientResource save(ClientResource clientResource);
    ClientResource update(Long id, ClientResource clientResource);
    void delete(Long id);
}
