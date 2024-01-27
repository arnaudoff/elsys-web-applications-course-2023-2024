package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class ClientContactResource{
    private Integer id;
    private ClientResource client;
    private String email;
    private String tel;
}