package org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientAuditMapper;
import org.elsys_bg.ElectronicsRepair.repository.audit.ClientAuditRepository;
import org.elsys_bg.ElectronicsRepair.service.audit.ClientAuditService;
import org.elsys_bg.ElectronicsRepair.service.impl.audit.ClientAuditServiceImpl;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClientAuditListener implements PostInsertEventListener, PostUpdateEventListener, PreDeleteEventListener{
    private final ClientAuditServiceImpl clientService;
    private final ClientAuditMapper clientAuditMapper;
    private final ClientMapper clientMapper;

    @Override
    public void onPostInsert(PostInsertEvent event) {
        performAudit(event.getEntity(), 'C');
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event){
        performAudit(event.getEntity(), 'U');
    }

    @Override
    public boolean onPreDelete(PreDeleteEvent event) {
        performAudit(event.getEntity(), 'D');
        return false;
    }

    private void performAudit(Object entity, char action){
        try{
            // Create a new instance of the audit entity
            ClientAuditResource clientAuditResource = new ClientAuditResource();
            Client auditEntity = (Client) entity;

            // Set values of audit entity class
            clientAuditResource.setClient(clientMapper.toClientResource(auditEntity));
            clientAuditResource.setName(auditEntity.getName());
            clientAuditResource.setPassword(auditEntity.getPassword());

            clientAuditResource.setAction(action);
            clientAuditResource.setTimestamp(LocalDateTime.now());

            // Save the audit entity using the audit service
            clientService.save(clientAuditMapper.fromClientAuditResource(clientAuditResource));
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}