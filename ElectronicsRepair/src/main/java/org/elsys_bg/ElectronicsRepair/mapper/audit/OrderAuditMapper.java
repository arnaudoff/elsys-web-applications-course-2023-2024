package org.elsys_bg.ElectronicsRepair.mapper.audit;

import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.entity.audit.OrderAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderAuditMapper{
    OrderAuditMapper MAPPER = Mappers.getMapper(OrderAuditMapper.class);

    @Mapping(target = "id", source = "orderAuditResource.id")
    @Mapping(target = "order", source = "orderAuditResource.order")
    @Mapping(target = "client", source = "orderAuditResource.client")
    @Mapping(target = "supportedDeviceType", source = "orderAuditResource.supportedDeviceType")
    @Mapping(target = "model", source = "orderAuditResource.model")
    @Mapping(target = "description", source = "orderAuditResource.description")
    @Mapping(target = "orderStatus", source = "orderAuditResource.orderStatus")
    @Mapping(target = "action", source = "orderAuditResource.action")
    @Mapping(target = "timestamp", source = "orderAuditResource.timestamp")
    OrderAudit fromOrderAuditResource(OrderAuditResource orderAuditResource);

    @Mapping(target = "id", source = "orderAudit.id")
    @Mapping(target = "order", source = "orderAudit.order")
    @Mapping(target = "client", source = "orderAudit.client")
    @Mapping(target = "supportedDeviceType", source = "orderAudit.supportedDeviceType")
    @Mapping(target = "model", source = "orderAudit.model")
    @Mapping(target = "description", source = "orderAudit.description")
    @Mapping(target = "orderStatus", source = "orderAudit.orderStatus")
    @Mapping(target = "action", source = "orderAudit.action")
    @Mapping(target = "timestamp", source = "orderAudit.timestamp")
    OrderAuditResource toOrderAuditResource(OrderAudit orderAudit);

    List<OrderAuditResource> toOrderAuditResources(List<OrderAudit> orderAudits);
}