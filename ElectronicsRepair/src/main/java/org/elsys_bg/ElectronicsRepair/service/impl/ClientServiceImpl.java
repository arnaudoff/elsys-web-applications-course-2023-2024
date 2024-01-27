package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.repository.ClientRepository;
import org.elsys_bg.ElectronicsRepair.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{
    public final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResource getById(Long clientId){
        return clientMapper.toClientResource(clientRepository.getById(clientId));
    }

    public ClientResource getByName(String clientName){
        return clientMapper.toClientResource(clientRepository.findByUsername(clientName));
    }

    @Override
    public List<ClientResource> findAll(){
        return clientMapper.toClientResources(clientRepository.findAll());
    }

    @Override
    public ClientResource save(Client client){
        return clientMapper.toClientResource(clientRepository.save(client));
    }

    @Override
    public void delete(Client client){
        clientRepository.delete(client);
    }

    @Override
    public ClientResource updateClient(Client client) throws NoSuchElementException{
        Client existingClient = clientRepository.findById(Long.valueOf(client.getId())).orElse(null);

        if(existingClient != null){
            existingClient.setName(client.getName());
            existingClient.setPassword(client.getPassword());
            clientRepository.save(existingClient);
        }else{
            throw new NoSuchElementException("ERR: Client with ID " + client.getId() + " does not exist.");
        }

        return clientMapper.toClientResource(existingClient);
    }

    public void deleteClientByName(String username){
        clientRepository.deleteByUsername(username);
    }

    public boolean userExists(String username){
        return clientRepository.userExists(username);
    }

    public boolean checkClientPassword(String username, String password){
        return clientRepository.checkClientPassword(username, password);
    }

    public ClientResource signUp(String username, String password){
        ClientResource newClient = new ClientResource();
        newClient.setName(username);
        newClient.setPassword(password);
        return clientMapper.toClientResource(clientRepository.save(clientMapper.fromClientResource(newClient)));
    }
}