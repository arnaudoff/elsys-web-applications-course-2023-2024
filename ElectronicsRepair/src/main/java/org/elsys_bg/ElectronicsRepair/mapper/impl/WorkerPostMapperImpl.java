package org.elsys_bg.ElectronicsRepair.mapper.impl;

import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerPostResource;
import org.elsys_bg.ElectronicsRepair.entity.WorkerPost;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerPostMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WorkerPostMapperImpl implements WorkerPostMapper{
    @Override
    public WorkerPost fromWorkerPostResource(WorkerPostResource workerPostResource){
        if(workerPostResource == null){
            return null;
        }

        WorkerPost workerPost = new WorkerPost();
        workerPost.setId(workerPostResource.getId());
        workerPost.setPost(workerPostResource.getPost());

        return workerPost;
    }

    @Override
    public WorkerPostResource toWorkerPostResource(WorkerPost workerPost){
        if(workerPost == null){
            return null;
        }

        WorkerPostResource workerPostResource = new WorkerPostResource();
        workerPostResource.setId(workerPost.getId());
        workerPostResource.setPost(workerPost.getPost());

        return workerPostResource;
    }

    @Override
    public List<WorkerPostResource> toWorkerPostResources(List<WorkerPost> workerPosts){
        if(workerPosts == null){
            return Collections.emptyList();
        }

        List<WorkerPostResource> workerPostResources = new ArrayList<>();
        for(WorkerPost workerPost : workerPosts){
            workerPostResources.add(toWorkerPostResource(workerPost));
        }

        return workerPostResources;
    }
}