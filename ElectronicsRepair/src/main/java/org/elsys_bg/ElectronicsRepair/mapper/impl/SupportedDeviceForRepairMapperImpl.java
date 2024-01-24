package org.elsys_bg.ElectronicsRepair.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.mapper.DeviceTypeMapper;
import org.elsys_bg.ElectronicsRepair.mapper.SupportedDeviceForRepairMapper;
import org.elsys_bg.ElectronicsRepair.miscellaneous.SupportedDeviceForRepairProjection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SupportedDeviceForRepairMapperImpl implements SupportedDeviceForRepairMapper{
    private final DeviceTypeMapper deviceTypeMapper;

    @Override
    public SupportedDeviceForRepair fromSupportedDeviceForRepairResource(SupportedDeviceForRepairResource supportedDeviceForRepairResource){
        if(supportedDeviceForRepairResource == null){
            return null;
        }

        SupportedDeviceForRepair supportedDeviceForRepair = new SupportedDeviceForRepair();
        supportedDeviceForRepair.setId(supportedDeviceForRepairResource.getId());
        supportedDeviceForRepair.setDeviceType(deviceTypeMapper.fromDeviceTypeResource(supportedDeviceForRepairResource.getDeviceType()));
        supportedDeviceForRepair.setManufacturer(supportedDeviceForRepairResource.getManufacturer());

        return supportedDeviceForRepair;
    }

    @Override
    public SupportedDeviceForRepairResource toSupportedDeviceForRepairResource(SupportedDeviceForRepair supportedDeviceForRepair){
        if(supportedDeviceForRepair == null){
            return null;
        }

        SupportedDeviceForRepairResource supportedDeviceForRepairResource = new SupportedDeviceForRepairResource();
        supportedDeviceForRepairResource.setId(supportedDeviceForRepair.getId());
        supportedDeviceForRepairResource.setDeviceType(deviceTypeMapper.toDeviceTypeResource(supportedDeviceForRepair.getDeviceType()));
        supportedDeviceForRepairResource.setManufacturer(supportedDeviceForRepair.getManufacturer());

        return supportedDeviceForRepairResource;
    }

    @Override
    public List<SupportedDeviceForRepairResource> toSupportedDeviceForRepairResources(List<SupportedDeviceForRepair> supportedDevicesForRepair){
        if(supportedDevicesForRepair == null){
            return Collections.emptyList();
        }

        List<SupportedDeviceForRepairResource> supportedDeviceForRepairResources = new ArrayList<>();
        for(SupportedDeviceForRepair supportedDeviceForRepair : supportedDevicesForRepair){
            supportedDeviceForRepairResources.add(toSupportedDeviceForRepairResource(supportedDeviceForRepair));
        }

        return supportedDeviceForRepairResources;
    }

    @Override
    public SupportedDeviceForRepairResource fromSupportedDeviceForRepairProjection(SupportedDeviceForRepairProjection supportedDeviceForRepairProjection){
        SupportedDeviceForRepairResource supportedDeviceForRepair = new SupportedDeviceForRepairResource();
        supportedDeviceForRepair.setId(supportedDeviceForRepairProjection.getId());
        supportedDeviceForRepair.setManufacturer(supportedDeviceForRepairProjection.getManufacturer());
        supportedDeviceForRepair.setDeviceType(deviceTypeMapper.fromDeviceTypeProjection(supportedDeviceForRepairProjection.getDeviceType()));
        return supportedDeviceForRepair;
    }
}