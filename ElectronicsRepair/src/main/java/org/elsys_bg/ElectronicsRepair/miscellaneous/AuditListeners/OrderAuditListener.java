package org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderStatusMapper;
import org.elsys_bg.ElectronicsRepair.mapper.SupportedDeviceForRepairMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.OrderAuditMapper;
import org.elsys_bg.ElectronicsRepair.service.impl.audit.OrderAuditServiceImpl;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderAuditListener implements PostInsertEventListener, PostUpdateEventListener, PreDeleteEventListener{
    private final OrderAuditServiceImpl orderAuditService;
    private final OrderAuditMapper orderAuditMapper;
    private final OrderMapper orderMapper;
    private final OrderStatusMapper orderStatusMapper;
    private final ClientMapper clientMapper;
    private final SupportedDeviceForRepairMapper supportedDeviceMapper;

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
            OrderAuditResource orderAuditResource = new OrderAuditResource();
            Order auditEntity = (Order) entity;

            // Set values of audit entity class
            orderAuditResource.setOrder(orderMapper.toOrderResource(auditEntity));
            orderAuditResource.setClient(clientMapper.toClientResource(auditEntity.getClient()));
            orderAuditResource.setSupportedDeviceType(supportedDeviceMapper.toSupportedDeviceForRepairResource(auditEntity.getSupportedDeviceType()));
            orderAuditResource.setOrderStatus(orderStatusMapper.toOrderStatusResource(auditEntity.getOrderStatus()));
            orderAuditResource.setDescription(auditEntity.getDescription());
            orderAuditResource.setModel(auditEntity.getModel());

            orderAuditResource.setAction(action);
            orderAuditResource.setTimestamp(LocalDateTime.now());

            // Save the audit entity using the audit service
            orderAuditService.save(orderAuditMapper.fromOrderAuditResource(orderAuditResource));
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister entityPersister) {
        return false;
    }
}