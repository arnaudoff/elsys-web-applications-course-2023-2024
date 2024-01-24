package org.elsys_bg.ElectronicsRepair.service.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.WorkerAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.WorkerAudit;

import java.util.List;

public interface WorkerAuditService{
    List<WorkerAuditResource> findAll();
    WorkerAuditResource save(WorkerAudit workerAudit);
}
