package org.elsys_bg.ElectronicsRepair.mapper.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.WorkerAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.WorkerAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerAuditMapper{
    WorkerAuditMapper MAPPER = Mappers.getMapper(WorkerAuditMapper.class);

    @Mapping(target = "Id", source = "workerAuditResource.Id")
    @Mapping(target = "client", source = "workerAuditResource.worker")
    @Mapping(target = "name", source = "workerAuditResource.name")
    @Mapping(target = "password", source = "workerAuditResource.password")
    @Mapping(target = "action", source = "workerAuditResource.action")
    @Mapping(target = "timestamp", source = "workerAuditResource.timestamp")
    WorkerAudit fromWorkerAuditResource(WorkerAuditResource workerAuditResource);

    @Mapping(target = "Id", source = "workerAudit.Id")
    @Mapping(target = "client", source = "workerAudit.worker")
    @Mapping(target = "name", source = "workerAudit.name")
    @Mapping(target = "password", source = "workerAudit.password")
    @Mapping(target = "action", source = "workerAudit.action")
    @Mapping(target = "timestamp", source = "workerAudit.timestamp")
    WorkerAuditResource toWorkerAuditResource(WorkerAudit workerAudit);

    List<WorkerAuditResource> toWorkerAuditResources(List<WorkerAudit> workerAudits);
}