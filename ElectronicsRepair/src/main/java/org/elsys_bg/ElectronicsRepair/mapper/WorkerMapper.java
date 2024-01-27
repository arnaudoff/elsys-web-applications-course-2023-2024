package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerMapper{
    WorkerMapper MAPPER = Mappers.getMapper(WorkerMapper.class);

    @Mapping(target = "id", source = "workerResource.id")
    @Mapping(target = "name", source = "workerResource.name")
    @Mapping(target = "password", source = "workerResource.password")
    @Mapping(target = "post", source = "workerResource.post")
    Worker fromWorkerResource(WorkerResource workerResource);

    @Mapping(target = "id", source = "worker.id")
    @Mapping(target = "name", source = "worker.name")
    @Mapping(target = "password", source = "worker.password")
    @Mapping(target = "post", source = "worker.post")
    WorkerResource toWorkerResource(Worker worker);

    List<WorkerResource> toWorkerResources(List<Worker> workers);
}