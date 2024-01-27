package org.elsys_bg.ElectronicsRepair.mapper.impl;

import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.ClientProjection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ClientMapperImpl implements ClientMapper{
    @Override
    public Client fromClientResource(ClientResource clientResource){
        if(clientResource == null){
            return null;
        }

        Client client = new Client();
        client.setId(clientResource.getId());
        client.setName(clientResource.getName());
        client.setPassword(clientResource.getPassword());

        return client;
    }

    @Override
    public ClientResource toClientResource(Client client){
        if(client == null){
            return null;
        }

        ClientResource clientResource = new ClientResource();
        clientResource.setId(client.getId());
        clientResource.setName(client.getName());
        clientResource.setPassword(client.getPassword());

        return clientResource;
    }

    @Override
    public List<ClientResource> toClientResources(List<Client> clients){
        if(clients == null){
            return Collections.emptyList();
        }

        List<ClientResource> clientResources = new ArrayList<>();
        for(Client client : clients){
            clientResources.add(toClientResource(client));
        }

        return clientResources;
    }

    @Override
    public ClientResource fromClientProjection(ClientProjection clientProjection){
        ClientResource client = new ClientResource();
        client.setId(clientProjection.getId());
        client.setName(clientProjection.getName());
        client.setPassword(clientProjection.getPassword());
        return client;
    }
}