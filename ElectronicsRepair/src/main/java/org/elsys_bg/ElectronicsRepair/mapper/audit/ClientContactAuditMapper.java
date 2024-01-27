package org.elsys_bg.ElectronicsRepair.mapper.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientContactAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientContactAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientContactAuditMapper{
    ClientContactAuditMapper MAPPER = Mappers.getMapper(ClientContactAuditMapper.class);

    @Mapping(target = "Id", source = "clientContactAuditResource.Id")
    @Mapping(target = "clientContact", source = "clientContactAuditResource.clientContact")
    @Mapping(target = "client", source = "clientContactAuditResource.client")
    @Mapping(target = "email", source = "clientContactAuditResource.email")
    @Mapping(target = "tel", source = "clientContactAuditResource.tel")
    @Mapping(target = "action", source = "clientContactAuditResource.action")
    @Mapping(target = "timestamp", source = "clientContactAuditResource.timestamp")
    ClientContactAudit fromClientContactAuditResource(ClientContactAuditResource clientContactAuditResource);

    @Mapping(target = "Id", source = "clientContactAudit.Id")
    @Mapping(target = "clientContact", source = "clientContactAudit.clientContact")
    @Mapping(target = "client", source = "clientContactAudit.client")
    @Mapping(target = "email", source = "clientContactAudit.email")
    @Mapping(target = "tel", source = "clientContactAudit.tel")
    @Mapping(target = "action", source = "clientContactAudit.action")
    @Mapping(target = "timestamp", source = "clientContactAudit.timestamp")
    ClientContactAuditResource toClientContactAuditResource(ClientContactAudit clientContactAudit);

    List<ClientContactAuditResource> toClientContactAuditResources(List<ClientContactAudit> clientContactAudits);
}
