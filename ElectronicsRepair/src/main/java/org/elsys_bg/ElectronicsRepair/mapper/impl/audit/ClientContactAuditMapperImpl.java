package org.elsys_bg.ElectronicsRepair.mapper.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientContactAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.ClientContactAudit;
import org.elsys_bg.ElectronicsRepair.mapper.ClientContactMapper;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientContactAuditMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientContactAuditMapperImpl implements ClientContactAuditMapper{
    private final ClientMapper clientMapper;
    private final ClientContactMapper clientContactMapper;

    @Override
    public ClientContactAudit fromClientContactAuditResource(ClientContactAuditResource clientContactAuditResource){
        if(clientContactAuditResource == null){
            return null;
        }

        ClientContactAudit adminAudit = new ClientContactAudit();
        adminAudit.setId(clientContactAuditResource.getId());
        adminAudit.setClientContact(clientContactMapper.fromClientContactResource(clientContactAuditResource.getClientContact()));
        adminAudit.setClient(clientMapper.fromClientResource(clientContactAuditResource.getClient()));
        adminAudit.setEmail(clientContactAuditResource.getEmail());
        adminAudit.setTel(clientContactAuditResource.getTel());
        adminAudit.setAction(clientContactAuditResource.getAction());
        adminAudit.setTimestamp(clientContactAuditResource.getTimestamp());

        return adminAudit;
    }

    @Override
    public ClientContactAuditResource toClientContactAuditResource(ClientContactAudit clientContactAudit){
        if(clientContactAudit == null){
            return null;
        }

        ClientContactAuditResource adminAuditResource = new ClientContactAuditResource();
        adminAuditResource.setId(clientContactAudit.getId());
        adminAuditResource.setClientContact(clientContactMapper.toClientContactResource(clientContactAudit.getClientContact()));
        adminAuditResource.setClient(clientMapper.toClientResource(clientContactAudit.getClient()));
        adminAuditResource.setEmail(clientContactAudit.getEmail());
        adminAuditResource.setTel(clientContactAudit.getTel());
        adminAuditResource.setAction(clientContactAudit.getAction());
        adminAuditResource.setTimestamp(clientContactAudit.getTimestamp());

        return adminAuditResource;
    }

    @Override
    public List<ClientContactAuditResource> toClientContactAuditResources(List<ClientContactAudit> clientContactAudits){
        if(clientContactAudits == null){
            return Collections.emptyList();
        }

        List<ClientContactAuditResource> adminAuditResources = new ArrayList<>();
        for(ClientContactAudit clientContactAudit : clientContactAudits){
            adminAuditResources.add(toClientContactAuditResource(clientContactAudit));
        }

        return adminAuditResources;
    }
}