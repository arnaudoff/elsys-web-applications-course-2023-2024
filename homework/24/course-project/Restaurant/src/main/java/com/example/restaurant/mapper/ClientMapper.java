package com.example.restaurant.mapper;

import com.example.restaurant.entity.Client;
import com.example.restaurant.controller.resources.ClientResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientMapper CLIENT_MAPPER = Mappers.getMapper(ClientMapper.class);
    Client fromClientResource(ClientResource clientResource);
    ClientResource toClientResource(Client client);
    List<ClientResource> toClientResourceList(List<Client> clients);
}
