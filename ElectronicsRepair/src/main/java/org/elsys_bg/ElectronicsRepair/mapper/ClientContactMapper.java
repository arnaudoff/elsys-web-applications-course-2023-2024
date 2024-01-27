package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.ClientContactResource;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientContactMapper{
    ClientContactMapper MAPPER = Mappers.getMapper(ClientContactMapper.class);

    @Mapping(target = "id", source = "clientContactResource.id")
    @Mapping(target = "client", source = "clientContactResource.client")
    @Mapping(target = "email", source = "clientContactResource.email")
    @Mapping(target = "tel", source = "clientContactResource.tel")
    ClientContact fromClientContactResource(ClientContactResource clientContactResource);

    @Mapping(target = "id", source = "clientContact.id")
    @Mapping(target = "client", source = "clientContact.client")
    @Mapping(target = "email", source = "clientContact.email")
    @Mapping(target = "tel", source = "clientContact.tel")
    ClientContactResource toClientContactResource(ClientContact clientContact);

    List<ClientContactResource> toClientContactResources(List<ClientContact> clientContacts);
}