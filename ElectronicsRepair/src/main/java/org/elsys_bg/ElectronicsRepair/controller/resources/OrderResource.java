package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class OrderResource{
    private Integer id;
    private ClientResource client;
    private SupportedDeviceForRepairResource supportedDeviceType;
    private String model;
    private String description;
    private OrderStatusResource orderStatus;
}