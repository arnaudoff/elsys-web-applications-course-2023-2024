package com.example.restaurant.service.impl;

import com.example.restaurant.controller.resources.ClientResource;
import com.example.restaurant.entity.Client;
import com.example.restaurant.repository.ClientRepository;
import com.example.restaurant.service.ClientService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.restaurant.mapper.ClientMapper.CLIENT_MAPPER;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientResource> getAll() {
        return CLIENT_MAPPER.toClientResourceList(clientRepository.findAll());
    }

    @Override
    public ClientResource getById(Long id) {
        return CLIENT_MAPPER.toClientResource(clientRepository.getReferenceById(id));
    }

    @Override
    public ClientResource save(ClientResource clientResource) {
        Client client = CLIENT_MAPPER.fromClientResource(clientResource);

        try {
            client = clientRepository.save(client);
        } catch (Exception e) {
            throw new EntityExistsException("Client already exists");
        }

        return CLIENT_MAPPER.toClientResource(client);
    }

    @Override
    public ClientResource update(Long id, ClientResource clientResource) {
        Client clientToUpdate = clientRepository.getReferenceById(id);
        clientToUpdate.setName(clientResource.getName());
        clientToUpdate.setEmail(clientResource.getEmail());
        clientToUpdate.setPhone(clientResource.getPhone());
        clientToUpdate.setAddress(clientResource.getAddress());

        clientToUpdate = clientRepository.save(clientToUpdate);
        return CLIENT_MAPPER.toClientResource(clientToUpdate);
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.getReferenceById(id);
        clientRepository.deleteById(id);
    }
}
