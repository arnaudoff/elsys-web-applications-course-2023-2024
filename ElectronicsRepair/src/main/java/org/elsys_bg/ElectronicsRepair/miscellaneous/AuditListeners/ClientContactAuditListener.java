package org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.ClientContactAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;
import org.elsys_bg.ElectronicsRepair.mapper.ClientContactMapper;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.ClientContactAuditMapper;
import org.elsys_bg.ElectronicsRepair.service.impl.audit.ClientContactAuditServiceImpl;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClientContactAuditListener implements PostInsertEventListener, PostUpdateEventListener, PreDeleteEventListener {
    private final ClientContactAuditServiceImpl clientContactService;
    private final ClientContactAuditMapper clientContactAuditMapper;
    private final ClientContactMapper clientContactMapper;
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
            ClientContactAuditResource clientContactAuditResource = new ClientContactAuditResource();
            ClientContact auditEntity = (ClientContact) entity;

            // Set values of audit entity class
            clientContactAuditResource.setId(auditEntity.getId());
            clientContactAuditResource.setClientContact(clientContactMapper.toClientContactResource(auditEntity));
            clientContactAuditResource.setClient(clientMapper.toClientResource(auditEntity.getClient()));
            clientContactAuditResource.setEmail(auditEntity.getEmail());
            clientContactAuditResource.setTel(auditEntity.getTel());

            clientContactAuditResource.setAction(action);
            clientContactAuditResource.setTimestamp(LocalDateTime.now());

            // Save the audit entity using the audit service
            clientContactService.save(clientContactAuditMapper.fromClientContactAuditResource(clientContactAuditResource));
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}