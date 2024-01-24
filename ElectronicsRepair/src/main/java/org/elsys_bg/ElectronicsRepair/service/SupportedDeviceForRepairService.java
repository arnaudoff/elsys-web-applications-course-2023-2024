package org.elsys_bg.ElectronicsRepair.service;

import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.repository.SupportedDeviceForRepairRepository;

import java.util.List;

public interface SupportedDeviceForRepairService{
    List<SupportedDeviceForRepairResource> findAll();

    SupportedDeviceForRepairResource save(SupportedDeviceForRepair supportedDeviceForRepair);

    void delete(SupportedDeviceForRepair device);

    SupportedDeviceForRepairResource updateSupportedDeviceForRepair(SupportedDeviceForRepair device);
}