package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.DeviceTypeResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;

import java.util.List;

public interface DeviceTypeService{
    List<DeviceTypeResource> findAll();

    DeviceTypeResource save(DeviceType deviceType);

    void delete(DeviceType deviceType);

    DeviceTypeResource updateDeviceType(DeviceType deviceType);
}