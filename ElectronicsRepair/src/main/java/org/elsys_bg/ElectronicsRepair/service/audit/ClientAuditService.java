package org.elsys_bg.ElectronicsRepair.service.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;

import java.util.List;

public interface ClientAuditService{
    List<ClientAuditResource> findAll();
    ClientAuditResource save(ClientAudit clientAudit);
}