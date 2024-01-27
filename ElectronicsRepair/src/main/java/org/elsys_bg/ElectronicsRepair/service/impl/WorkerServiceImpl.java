package org.elsys_bg.ElectronicsRepair.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.elsys_bg.ElectronicsRepair.entity.WorkerPost;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerMapper;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerPostMapper;
import org.elsys_bg.ElectronicsRepair.repository.WorkerPostRepository;
import org.elsys_bg.ElectronicsRepair.repository.WorkerRepository;
import org.elsys_bg.ElectronicsRepair.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService{
    public final WorkerRepository workerRepository;
    public final WorkerPostRepository workerPostRepository;
    public final WorkerMapper workerMapper;
    public final WorkerPostMapper workerPostMapper;

    public WorkerResource getById(Long workerId){
        return workerMapper.toWorkerResource(workerRepository.getById(workerId));
    }

    @Override
    public List<WorkerResource> findAll(){
        return workerMapper.toWorkerResources(workerRepository.findAll());
    }

    @Override
    public WorkerResource save(Worker worker){
        return workerMapper.toWorkerResource(workerRepository.save(worker));
    }

    @Override
    public void delete(Worker worker){
        workerRepository.delete(worker);
    }

    @Override
    public WorkerResource updateWorker(Worker worker) throws NoSuchElementException{
        Worker existingWorker = workerRepository.findById(Long.valueOf(worker.getId())).orElse(null);

        if(existingWorker != null){
            existingWorker.setName(worker.getName());
            existingWorker.setPassword(worker.getPassword());
            workerRepository.save(existingWorker);
        }else{
            throw new NoSuchElementException("ERR: Worker with ID " + worker.getId() + " does not exist.");
        }

        return workerMapper.toWorkerResource(existingWorker);
    }

    public boolean workerExists(String username){
        return workerRepository.workerExists(username);
    }
    public boolean adminExists(String username){
        return workerRepository.adminExists(username);
    }

    public boolean checkWorkerPassword(String username, String password){
        return workerRepository.checkWorkerPassword(username, password);
    }
    public boolean checkAdminPassword(String username, String password){
        return workerRepository.checkAdminPassword(username, password);
    }

    public WorkerResource signUp(String username, String password, WorkerPost post){
        WorkerResource newWorker = new WorkerResource();
        newWorker.setName(username);
        newWorker.setPassword(password);
        newWorker.setPost(workerPostMapper.toWorkerPostResource(post));
        return workerMapper.toWorkerResource(workerRepository.save(workerMapper.fromWorkerResource(newWorker)));
    }
}