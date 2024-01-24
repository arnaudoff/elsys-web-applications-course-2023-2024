package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerPostResource;
import org.elsys_bg.ElectronicsRepair.entity.WorkerPost;

import java.util.List;

public interface WorkerPostService{
    List<WorkerPostResource> findAll();

    WorkerPostResource save(WorkerPost workerPost);

    void delete(WorkerPost post);

    WorkerPostResource updateWorkerPost(WorkerPost post);
}