package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class WorkerResource{
    private Integer id;
    private String name;
    private String password;
    private WorkerPostResource post;
}