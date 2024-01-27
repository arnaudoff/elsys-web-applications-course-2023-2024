package org.elsys_bg.ElectronicsRepair.controller.resources.audit;

import lombok.Data;
import org.elsys_bg.ElectronicsRepair.controller.resources.WorkerResource;

import java.time.LocalDateTime;

@Data
public class WorkerAuditResource{
    private Integer Id;
    private WorkerResource worker;
    private String name;
    private String password;
    private char action;
    private LocalDateTime timestamp;
}