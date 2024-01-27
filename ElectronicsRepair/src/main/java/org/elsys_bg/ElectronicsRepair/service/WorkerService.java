package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;

import java.util.List;

public interface WorkerService{
    List<WorkerResource> findAll();

    WorkerResource save(Worker worker);

    void delete(Worker worker);

    WorkerResource updateWorker(Worker worker);
}