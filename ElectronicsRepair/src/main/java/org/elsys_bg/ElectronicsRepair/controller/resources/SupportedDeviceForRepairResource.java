package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class SupportedDeviceForRepairResource{
    private Integer id;
    private DeviceTypeResource deviceType;
    private String manufacturer;
}