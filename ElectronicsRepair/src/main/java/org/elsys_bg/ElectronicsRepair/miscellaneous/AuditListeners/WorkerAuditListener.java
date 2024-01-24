package org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.WorkerAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.elsys_bg.ElectronicsRepair.mapper.WorkerMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.WorkerAuditMapper;
import org.elsys_bg.ElectronicsRepair.service.impl.audit.WorkerAuditServiceImpl;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WorkerAuditListener implements PostInsertEventListener, PostUpdateEventListener, PreDeleteEventListener {
    private final WorkerAuditServiceImpl workerService;
    private final WorkerAuditMapper workerAuditMapper;
    private final WorkerMapper workerMapper;

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
            WorkerAuditResource workerAuditResource = new WorkerAuditResource();
            Worker auditEntity = (Worker) entity;

            // Set values of audit entity class
            workerAuditResource.setWorker(workerMapper.toWorkerResource(auditEntity));
            workerAuditResource.setName(auditEntity.getName());
            workerAuditResource.setPassword(auditEntity.getPassword());

            workerAuditResource.setAction(action);
            workerAuditResource.setTimestamp(LocalDateTime.now());

            // Save the audit entity using the audit service
            workerService.save(workerAuditMapper.fromWorkerAuditResource(workerAuditResource));
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}