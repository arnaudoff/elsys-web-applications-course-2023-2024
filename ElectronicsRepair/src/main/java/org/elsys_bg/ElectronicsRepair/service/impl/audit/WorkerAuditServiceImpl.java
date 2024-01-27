package org.elsys_bg.ElectronicsRepair.service.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.WorkerAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.WorkerAudit;
import org.elsys_bg.ElectronicsRepair.mapper.audit.WorkerAuditMapper;
import org.elsys_bg.ElectronicsRepair.repository.audit.WorkerAuditRepository;
import org.elsys_bg.ElectronicsRepair.service.audit.WorkerAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerAuditServiceImpl implements WorkerAuditService{
    public final WorkerAuditRepository workerAuditRepository;
    private final WorkerAuditMapper workerAuditMapper;

    @Override
    public List<WorkerAuditResource> findAll(){
        return workerAuditMapper.toWorkerAuditResources(workerAuditRepository.findAll());
    }

    @Override
    public WorkerAuditResource save(WorkerAudit workerAudit){
        return workerAuditMapper.toWorkerAuditResource(workerAuditRepository.save(workerAudit));
    }
}