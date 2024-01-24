package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.miscellaneous.ClientProjection;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper{
    ClientMapper MAPPER = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", source = "clientResource.id")
    @Mapping(target = "name", source = "clientResource.name")
    @Mapping(target = "password", source = "clientResource.password")
    Client fromClientResource(ClientResource clientResource);

    @Mapping(target = "id", source = "client.id")
    @Mapping(target = "name", source = "client.name")
    @Mapping(target = "password", source = "client.password")
    ClientResource toClientResource(Client client);

    List<ClientResource> toClientResources(List<Client> clients);

    ClientResource fromClientProjection(ClientProjection clientProjection);
}