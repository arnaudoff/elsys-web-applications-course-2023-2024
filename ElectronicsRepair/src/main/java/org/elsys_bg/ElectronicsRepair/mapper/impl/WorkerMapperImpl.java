package org.elsys_bg.ElectronicsRepair.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerMapper;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerPostMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkerMapperImpl implements WorkerMapper{
    private final WorkerPostMapper workerPostMapper;

    @Override
    public Worker fromWorkerResource(WorkerResource workerResource){
        if(workerResource == null){
            return null;
        }

        Worker worker = new Worker();
        worker.setId(workerResource.getId());
        worker.setName(workerResource.getName());
        worker.setPassword(workerResource.getPassword());
        worker.setPost(workerPostMapper.fromWorkerPostResource(workerResource.getPost()));

        return worker;
    }

    @Override
    public WorkerResource toWorkerResource(Worker worker){
        if(worker == null){
            return null;
        }

        WorkerResource workerResource = new WorkerResource();
        workerResource.setId(worker.getId());
        workerResource.setName(worker.getName());
        workerResource.setPassword(worker.getPassword());
        workerResource.setPost(workerPostMapper.toWorkerPostResource(worker.getPost()));

        return workerResource;
    }

    @Override
    public List<WorkerResource> toWorkerResources(List<Worker> workers){
        if(workers == null){
            return Collections.emptyList();
        }

        List<WorkerResource> workerResources = new ArrayList<>();
        for(Worker worker : workers){
            workerResources.add(toWorkerResource(worker));
        }

        return workerResources;
    }
}