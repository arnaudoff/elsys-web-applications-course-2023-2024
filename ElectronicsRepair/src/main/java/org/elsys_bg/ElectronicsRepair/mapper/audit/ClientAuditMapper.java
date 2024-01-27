package org.elsys_bg.ElectronicsRepair.mapper.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientAuditMapper{
    ClientAuditMapper MAPPER = Mappers.getMapper(ClientAuditMapper.class);

    @Mapping(target = "Id", source = "clientAuditResource.Id")
    @Mapping(target = "client", source = "clientAuditResource.client")
    @Mapping(target = "name", source = "clientAuditResource.name")
    @Mapping(target = "password", source = "clientAuditResource.password")
    @Mapping(target = "action", source = "clientAuditResource.action")
    @Mapping(target = "timestamp", source = "clientAuditResource.timestamp")
    ClientAudit fromClientAuditResource(ClientAuditResource clientAuditResource);

    @Mapping(target = "Id", source = "clientAudit.Id")
    @Mapping(target = "client", source = "clientAudit.client")
    @Mapping(target = "name", source = "clientAudit.name")
    @Mapping(target = "password", source = "clientAudit.password")
    @Mapping(target = "action", source = "clientAudit.action")
    @Mapping(target = "timestamp", source = "clientAudit.timestamp")
    ClientAuditResource toClientAuditResource(ClientAudit clientAudit);

    List<ClientAuditResource> toClientAuditResources(List<ClientAudit> clientAudits);
}