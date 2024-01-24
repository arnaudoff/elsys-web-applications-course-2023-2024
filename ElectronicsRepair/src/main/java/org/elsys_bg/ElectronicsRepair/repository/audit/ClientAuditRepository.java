package org.elsys_bg.ElectronicsRepair.repository.audit;

import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAuditRepository extends JpaRepository<ClientAudit, Long>{
}