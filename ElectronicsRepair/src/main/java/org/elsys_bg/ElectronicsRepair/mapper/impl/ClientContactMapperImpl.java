package org.elsys_bg.ElectronicsRepair.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientContactResource;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;
import org.elsys_bg.ElectronicsRepair.mapper.ClientContactMapper;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientContactMapperImpl implements ClientContactMapper{
    private final ClientMapper clientMapper;

    @Override
    public ClientContact fromClientContactResource(ClientContactResource clientContactResource){
        if(clientContactResource == null){
            return null;
        }

        ClientContact clientContact = new ClientContact();
        clientContact.setId(clientContactResource.getId());
        clientContact.setClient(clientMapper.fromClientResource(clientContactResource.getClient()));
        clientContact.setEmail(clientContactResource.getEmail());
        clientContact.setTel(clientContactResource.getTel());

        return clientContact;
    }

    @Override
    public ClientContactResource toClientContactResource(ClientContact clientContact){
        if(clientContact == null){
            return null;
        }

        ClientContactResource clientContactResource = new ClientContactResource();
        clientContactResource.setId(clientContact.getId());
        clientContactResource.setClient(clientMapper.toClientResource(clientContact.getClient()));
        clientContactResource.setEmail(clientContact.getEmail());
        clientContactResource.setTel(clientContact.getTel());

        return clientContactResource;
    }

    @Override
    public List<ClientContactResource> toClientContactResources(List<ClientContact> clientContacts){
        if(clientContacts == null){
            return Collections.emptyList();
        }

        List<ClientContactResource> clientContactResources = new ArrayList<>();
        for(ClientContact clientContact : clientContacts){
            clientContactResources.add(toClientContactResource(clientContact));
        }

        return clientContactResources;
    }
}