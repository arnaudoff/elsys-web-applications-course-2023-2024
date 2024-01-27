package org.elsys_bg.ElectronicsRepair.miscellaneous;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners.ClientContactAuditListener;
import org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners.ClientAuditListener;
import org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners.OrderAuditListener;
import org.elsys_bg.ElectronicsRepair.miscellaneous.AuditListeners.WorkerAuditListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfiguration{
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ClientAuditListener clientAuditListener;

    @Autowired
    private ClientContactAuditListener clientContactAuditListener;

    @Autowired
    private WorkerAuditListener workerAuditListener;

    @Autowired
    private OrderAuditListener orderAuditListener;

    @PostConstruct
    public void init(){
        SessionFactoryImpl sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);

        // Clients table event listener (trigger)
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(clientAuditListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(clientAuditListener);
        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(clientAuditListener);

        // Admins table event listener (trigger)
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(clientContactAuditListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(clientContactAuditListener);
        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(clientContactAuditListener);

        // Admins table event listener (trigger)
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(workerAuditListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(workerAuditListener);
        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(workerAuditListener);

        // Orders table event listener (trigger)
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(orderAuditListener);
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(orderAuditListener);
        registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(orderAuditListener);
    }
}