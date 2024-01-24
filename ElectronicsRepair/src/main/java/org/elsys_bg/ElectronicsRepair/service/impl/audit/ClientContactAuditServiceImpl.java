package org.elsys_bg.ElectronicsRepair.service.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientContactAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientContactAudit;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientContactAuditMapper;
import org.elsys_bg.ElectronicsRepair.repository.audit.ClientContactAuditRepository;
import org.elsys_bg.ElectronicsRepair.service.audit.ClientContactAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientContactAuditServiceImpl implements ClientContactAuditService {
    public final ClientContactAuditRepository clientContactAuditRepository;
    private final ClientContactAuditMapper clientContactAuditMapper;

    @Override
    public List<ClientContactAuditResource> findAll(){
        return clientContactAuditMapper.toClientContactAuditResources(clientContactAuditRepository.findAll());
    }

    @Override
    public ClientContactAuditResource save(ClientContactAudit clientContactAudit){
        return clientContactAuditMapper.toClientContactAuditResource(clientContactAuditRepository.save(clientContactAudit));
    }
}
