package org.elsys_bg.ElectronicsRepair.controller.resources;

import lombok.Data;

@Data
public class ReviewResource{
    private Integer id;
    private ClientResource client;
    private String reviewText;
}