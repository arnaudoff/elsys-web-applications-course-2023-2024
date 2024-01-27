package org.elsys_bg.ElectronicsRepair.mapper;

import org.elsys_bg.ElectronicsRepair.controller.resources.DeviceTypeResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.miscellaneous.DeviceTypeProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeviceTypeMapper{
    DeviceTypeMapper MAPPER = Mappers.getMapper(DeviceTypeMapper.class);

    @Mapping(target = "id", source = "deviceTypeResource.id")
    @Mapping(target = "deviceType", source = "deviceTypeResource.deviceType")
    DeviceType fromDeviceTypeResource(DeviceTypeResource deviceTypeResource);

    @Mapping(target = "id", source = "deviceType.id")
    @Mapping(target = "deviceType", source = "deviceType.deviceType")
    DeviceTypeResource toDeviceTypeResource(DeviceType deviceType);

    List<DeviceTypeResource> toDeviceTypeResources(List<DeviceType> deviceTypes);

    DeviceTypeResource fromDeviceTypeProjection(DeviceTypeProjection deviceType);
}