package me.bozhilov.EndMonitor.controller.resources;

import java.util.List;

import lombok.Data;

@Data
public class APIResource {

    private Long id;

    private String route;

    private String description;

    private String company;

    private List<EndpointResource> endpoints;
}
