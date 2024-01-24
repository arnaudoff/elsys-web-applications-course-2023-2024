package org.elsys_bg.ElectronicsRepair.service.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.OrderAudit;
import org.elsys_bg.ElectronicsRepair.mapper.audit.OrderAuditMapper;
import org.elsys_bg.ElectronicsRepair.repository.audit.OrderAuditRepository;
import org.elsys_bg.ElectronicsRepair.service.audit.OrderAuditService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAuditServiceImpl implements OrderAuditService{
    public final OrderAuditRepository orderAuditRepository;
    public final OrderAuditMapper orderAuditMapper;

    @Override
    public List<OrderAuditResource> findAll(){
        return orderAuditMapper.toOrderAuditResources(orderAuditRepository.findAll());
    }

    public List<OrderAuditResource> getFinishedOrders(){
        return orderAuditMapper.toOrderAuditResources(orderAuditRepository.getFinishedOrders());
    }

    public List<OrderAuditResource> getOrdersAfterDatetime(LocalDateTime timestamp){
        return orderAuditMapper.toOrderAuditResources(orderAuditRepository.getOrdersAfterDatetime(timestamp));
    }

    @Override
    public OrderAuditResource save(OrderAudit orderAudit){
        return orderAuditMapper.toOrderAuditResource(orderAuditRepository.save(orderAudit));
    }
}