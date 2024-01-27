package org.elsys_bg.ElectronicsRepair.mapper.impl.audit;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.WorkerAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;
import org.elsys_bg.ElectronicsRepair.entity.audit.WorkerAudit;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.WorkerAuditMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkerAuditMapperImpl implements WorkerAuditMapper{
    private final WorkerMapper workerMapper;

    @Override
    public WorkerAudit fromWorkerAuditResource(WorkerAuditResource workerAuditResource){
        if(workerAuditResource == null){
            return null;
        }

        WorkerAudit workerAudit = new WorkerAudit();
        workerAudit.setId(workerAuditResource.getId());
        workerAudit.setWorker(workerMapper.fromWorkerResource(workerAuditResource.getWorker()));
        workerAudit.setName(workerAuditResource.getName());
        workerAudit.setPassword(workerAuditResource.getPassword());
        workerAudit.setAction(workerAuditResource.getAction());
        workerAudit.setTimestamp(workerAuditResource.getTimestamp());

        return workerAudit;
    }

    @Override
    public WorkerAuditResource toWorkerAuditResource(WorkerAudit workerAudit){
        if(workerAudit == null){
            return null;
        }

        WorkerAuditResource workerAuditResource = new WorkerAuditResource();
        workerAuditResource.setId(workerAudit.getId());
        workerAuditResource.setWorker(workerMapper.toWorkerResource(workerAudit.getWorker()));
        workerAuditResource.setName(workerAudit.getName());
        workerAuditResource.setPassword(workerAudit.getPassword());
        workerAuditResource.setAction(workerAudit.getAction());
        workerAuditResource.setTimestamp(workerAudit.getTimestamp());

        return workerAuditResource;
    }

    @Override
    public List<WorkerAuditResource> toWorkerAuditResources(List<WorkerAudit> workerAudits){
        if(workerAudits == null){
            return Collections.emptyList();
        }

        List<WorkerAuditResource> workerAuditResources = new ArrayList<>();
        for(WorkerAudit workerAudit : workerAudits){
            workerAuditResources.add(toWorkerAuditResource(workerAudit));
        }

        return workerAuditResources;
    }
}