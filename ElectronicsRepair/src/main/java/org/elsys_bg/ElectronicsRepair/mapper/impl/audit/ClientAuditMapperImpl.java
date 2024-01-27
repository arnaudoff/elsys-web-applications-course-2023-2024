package org.elsys_bg.ElectronicsRepair.mapper.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientAudit;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientAuditMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientAuditMapperImpl implements ClientAuditMapper{
    private final ClientMapper clientMapper;

    @Override
    public ClientAudit fromClientAuditResource(ClientAuditResource clientAuditResource){
        if(clientAuditResource == null){
            return null;
        }

        ClientAudit clientAudit = new ClientAudit();
        clientAudit.setId(clientAuditResource.getId());
        clientAudit.setClient(clientMapper.fromClientResource(clientAuditResource.getClient()));
        clientAudit.setName(clientAuditResource.getName());
        clientAudit.setPassword(clientAuditResource.getPassword());
        clientAudit.setAction(clientAuditResource.getAction());
        clientAudit.setTimestamp(clientAuditResource.getTimestamp());

        return clientAudit;
    }

    @Override
    public ClientAuditResource toClientAuditResource(ClientAudit clientAudit){
        if(clientAudit == null){
            return null;
        }

        ClientAuditResource clientAuditResource = new ClientAuditResource();
        clientAuditResource.setId(clientAudit.getId());
        clientAuditResource.setClient(clientMapper.toClientResource(clientAudit.getClient()));
        clientAuditResource.setName(clientAudit.getName());
        clientAuditResource.setPassword(clientAudit.getPassword());
        clientAuditResource.setAction(clientAudit.getAction());
        clientAuditResource.setTimestamp(clientAudit.getTimestamp());

        return clientAuditResource;
    }

    @Override
    public List<ClientAuditResource> toClientAuditResources(List<ClientAudit> clientAudits){
        if(clientAudits == null){
            return Collections.emptyList();
        }

        List<ClientAuditResource> clientAuditResources = new ArrayList<>();
        for(ClientAudit clientAudit : clientAudits){
            clientAuditResources.add(toClientAuditResource(clientAudit));
        }

        return clientAuditResources;
    }
}