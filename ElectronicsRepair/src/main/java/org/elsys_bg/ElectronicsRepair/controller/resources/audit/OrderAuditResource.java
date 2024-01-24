package org.elsys_bg.ElectronicsRepair.controller.resources.audit;

import lombok.Data;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;

import java.time.LocalDateTime;

@Data
public class OrderAuditResource{
    private Integer id;
    private OrderResource order;
    private ClientResource client;
    private SupportedDeviceForRepairResource supportedDeviceType;
    private String model;
    private String description;
    private OrderStatusResource orderStatus;
    private char action;
    private LocalDateTime timestamp;
}