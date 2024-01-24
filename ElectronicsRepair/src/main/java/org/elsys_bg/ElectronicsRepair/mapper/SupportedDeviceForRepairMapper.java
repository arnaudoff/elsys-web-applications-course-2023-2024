package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.miscellaneous.SupportedDeviceForRepairProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SupportedDeviceForRepairMapper{
    SupportedDeviceForRepairMapper MAPPER = Mappers.getMapper(SupportedDeviceForRepairMapper.class);

    @Mapping(target = "id", source = "supportedDeviceForRepairResource.id")
    @Mapping(target = "deviceType", source = "supportedDeviceForRepairResource.deviceType")
    @Mapping(target = "manufacturer", source = "supportedDeviceForRepairResource.manufacturer")
    SupportedDeviceForRepair fromSupportedDeviceForRepairResource(SupportedDeviceForRepairResource supportedDeviceForRepairResource);

    @Mapping(target = "id", source = "supportedDeviceForRepair.id")
    @Mapping(target = "deviceType", source = "supportedDeviceForRepair.deviceType")
    @Mapping(target = "manufacturer", source = "supportedDeviceForRepair.manufacturer")
    SupportedDeviceForRepairResource toSupportedDeviceForRepairResource(SupportedDeviceForRepair supportedDeviceForRepair);

    List<SupportedDeviceForRepairResource> toSupportedDeviceForRepairResources(List<SupportedDeviceForRepair> supportedDevicesForRepair);

    SupportedDeviceForRepairResource fromSupportedDeviceForRepairProjection(SupportedDeviceForRepairProjection supportedDeviceForRepairProjection);
}