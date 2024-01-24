package org.elsys_bg.ElectronicsRepair.repository.audit;

import org.elsys_bg.ElectronicsRepair.entity.audit.WorkerAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerAuditRepository extends JpaRepository<WorkerAudit, Long>{
}