package org.elsys_bg.ElectronicsRepair.service.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientContactAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientContactAudit;

import java.util.List;

public interface ClientContactAuditService{
    List<ClientContactAuditResource> findAll();
    ClientContactAuditResource save(ClientContactAudit clientContactAudit);
}