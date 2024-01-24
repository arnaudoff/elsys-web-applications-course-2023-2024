package org.elsys_bg.ElectronicsRepair.mapper.impl.audit;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.OrderAudit;
import org.elsys_bg.ElectronicsRepair.mapper.ClientMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderMapper;
import org.elsys_bg.ElectronicsRepair.mapper.OrderStatusMapper;
import org.elsys_bg.ElectronicsRepair.mapper.SupportedDeviceForRepairMapper;
import org.elsys_bg.ElectronicsRepair.mapper.audit.OrderAuditMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAuditMapperImpl implements OrderAuditMapper{
    private final OrderMapper orderMapper;
    private final ClientMapper clientMapper;
    private final SupportedDeviceForRepairMapper supportedDeviceMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public OrderAudit fromOrderAuditResource(OrderAuditResource orderAuditResource){
        if(orderAuditResource == null){
            return null;
        }

        OrderAudit orderAudit = new OrderAudit();
        orderAudit.setId(orderAuditResource.getId());
        orderAudit.setOrder(orderMapper.fromOrderResource(orderAuditResource.getOrder()));
        orderAudit.setClient(clientMapper.fromClientResource(orderAuditResource.getClient()));
        orderAudit.setSupportedDeviceType(supportedDeviceMapper.fromSupportedDeviceForRepairResource(orderAuditResource.getSupportedDeviceType()));
        orderAudit.setModel(orderAuditResource.getModel());
        orderAudit.setDescription(orderAuditResource.getDescription());
        orderAudit.setOrderStatus(orderStatusMapper.fromOrderStatusResource(orderAuditResource.getOrderStatus()));
        orderAudit.setAction(orderAuditResource.getAction());
        orderAudit.setTimestamp(orderAuditResource.getTimestamp());
        return orderAudit;
    }

    @Override
    public OrderAuditResource toOrderAuditResource(OrderAudit orderAudit){
        if(orderAudit == null){
            return null;
        }

        OrderAuditResource orderAuditResource = new OrderAuditResource();
        orderAuditResource.setId(orderAudit.getId());
        orderAuditResource.setOrder(orderMapper.toOrderResource(orderAudit.getOrder()));
        orderAuditResource.setClient(clientMapper.toClientResource(orderAudit.getClient()));
        orderAuditResource.setSupportedDeviceType(supportedDeviceMapper.toSupportedDeviceForRepairResource(orderAudit.getSupportedDeviceType()));
        orderAuditResource.setModel(orderAudit.getModel());
        orderAuditResource.setDescription(orderAudit.getDescription());
        orderAuditResource.setOrderStatus(orderStatusMapper.toOrderStatusResource(orderAudit.getOrderStatus()));
        orderAuditResource.setAction(orderAudit.getAction());
        orderAuditResource.setTimestamp(orderAudit.getTimestamp());
        return orderAuditResource;
    }

    @Override
    public List<OrderAuditResource> toOrderAuditResources(List<OrderAudit> orderAudits){
        if(orderAudits == null){
            return null;
        }

        return orderAudits.stream()
                .map(this::toOrderAuditResource)
                .collect(Collectors.toList());
    }
}