package org.elsys_bg.ElectronicsRepair.controller.resources.audit;

import lombok.Data;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientContactResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;

import java.time.LocalDateTime;

@Data
public class ClientContactAuditResource{
    private Integer Id;
    private ClientContactResource clientContact;
    private ClientResource client;
    private String email;
    private String tel;
    private char action;
    private LocalDateTime timestamp;
}