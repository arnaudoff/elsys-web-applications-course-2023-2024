package org.elsys_bg.ElectronicsRepair.service.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.OrderAudit;

import java.util.List;

public interface OrderAuditService{
    List<OrderAuditResource> findAll();
    OrderAuditResource save(OrderAudit orderAudit);
}