package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class ClientResource{
    private Integer id;
    private String name;
    private String password;
}