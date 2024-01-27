package org.elsys_bg.ElectronicsRepair.mapper.impl;

import org.elsys_bg.ElectronicsRepair.controller.resources.DeviceTypeResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.mapper.DeviceTypeMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.DeviceTypeProjection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DeviceTypeMapperImpl implements DeviceTypeMapper{
    @Override
    public DeviceType fromDeviceTypeResource(DeviceTypeResource deviceTypeResource){
        if(deviceTypeResource == null){
            return null;
        }

        DeviceType deviceType = new DeviceType();
        deviceType.setId(deviceTypeResource.getId());
        deviceType.setDeviceType(deviceTypeResource.getDeviceType());

        return deviceType;
    }

    @Override
    public DeviceTypeResource toDeviceTypeResource(DeviceType deviceType){
        if(deviceType == null){
            return null;
        }

        DeviceTypeResource deviceTypeResource = new DeviceTypeResource();
        deviceTypeResource.setId(deviceType.getId());
        deviceTypeResource.setDeviceType(deviceType.getDeviceType());

        return deviceTypeResource;
    }

    @Override
    public List<DeviceTypeResource> toDeviceTypeResources(List<DeviceType> deviceTypes){
        if(deviceTypes == null){
            return Collections.emptyList();
        }

        List<DeviceTypeResource> deviceTypeResources = new ArrayList<>();
        for(DeviceType deviceType : deviceTypes){
            deviceTypeResources.add(toDeviceTypeResource(deviceType));
        }

        return deviceTypeResources;
    }

    @Override
    public DeviceTypeResource fromDeviceTypeProjection(DeviceTypeProjection deviceTypeProjection){
        DeviceTypeResource deviceType = new DeviceTypeResource();
        deviceType.setId(deviceTypeProjection.getId());
        deviceType.setDeviceType(deviceTypeProjection.getDeviceType());
        return deviceType;
    }
}