package org.elsys_bg.ElectronicsRepair.controller.resources.audit;

import lombok.Data;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;

import java.time.LocalDateTime;

@Data
public class ClientAuditResource{
    private Integer Id;
    private ClientResource client;
    private String name;
    private String password;
    private char action;
    private LocalDateTime timestamp;
}