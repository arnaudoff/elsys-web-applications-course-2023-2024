package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerPostResource;
import org.elsys_bg.ElectronicsRepair.entity.WorkerPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WorkerPostMapper{
    WorkerPostMapper MAPPER = Mappers.getMapper(WorkerPostMapper.class);

    @Mapping(target = "id", source = "workerPostResource.id")
    @Mapping(target = "post", source = "workerPostResource.post")
    WorkerPost fromWorkerPostResource(WorkerPostResource workerPostResource);

    @Mapping(target = "id", source = "workerPost.id")
    @Mapping(target = "post", source = "workerPost.post")
    WorkerPostResource toWorkerPostResource(WorkerPost workerPost);

    List<WorkerPostResource> toWorkerPostResources(List<WorkerPost> workerPosts);
}