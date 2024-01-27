package org.elsys_bg.ElectronicsRepair.service.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientAuditMapper;
import org.elsys_bg.ElectronicsRepair.repository.audit.ClientAuditRepository;
import org.elsys_bg.ElectronicsRepair.service.audit.ClientAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientAuditServiceImpl implements ClientAuditService{
    public final ClientAuditRepository clientAuditRepository;
    private final ClientAuditMapper clientAuditMapper;

    @Override
    public List<ClientAuditResource> findAll(){
        return clientAuditMapper.toClientAuditResources(clientAuditRepository.findAll());
    }

    @Override
    public ClientAuditResource save(ClientAudit clientAudit){
        return clientAuditMapper.toClientAuditResource(clientAuditRepository.save(clientAudit));
    }
}